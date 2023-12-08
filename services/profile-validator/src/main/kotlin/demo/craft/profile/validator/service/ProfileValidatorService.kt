package demo.craft.profile.validator.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.Product
import demo.craft.common.domain.enums.State
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.common.domain.kafka.impl.UserProfileValidationConfirmationMessage
import demo.craft.common.domain.kafka.impl.UserProfileValidationRequestMessage
import demo.craft.profile.validator.ValidationDecision
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.communication.publisher.ProfileValidatorPublisher
import demo.craft.profile.validator.dao.ProfileValidatorWorkflowAccess
import demo.craft.profile.validator.entity.enums.ValidationType
import demo.craft.profile.validator.service.validation.ValidationStrategyRetriever
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.Instant

@Component
class ProfileValidatorService(
    private val userProfileValidatorWorkflowAccess: ProfileValidatorWorkflowAccess,
    private val validationStrategyRetriever: ValidationStrategyRetriever,
    private val profileValidatorPublisher: ProfileValidatorPublisher
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    private val productToValidationTypeMap = mapOf<Product, ValidationType>(
        Product.QUICKBOOKS_ACCOUNTING to ValidationType.QUICKBOOKS_ACCOUNTING,
        Product.QUICKBOOKS_PAYMENTS to ValidationType.QUICKBOOKS_PAYMENTS,
        Product.QUICKBOOKS_PAYROLL to ValidationType.QUICKBOOKS_PAYROLL,
        Product.TSHEETS to ValidationType.TSHEETS
    )

    /**
     * Algorithm:
     * Profile validation returns "ACCEPTED" only if all validations (at each product level) are true.
     * If any one of the product level validations are un-successful then reject the request.
     */
    fun createUserProfileValidatorWorkflow(userProfileValidationRequestMessage: UserProfileValidationRequestMessage) {
        val userId = userProfileValidationRequestMessage.userId
        val requestId = userProfileValidationRequestMessage.requestId
        val userProfileMessage = userProfileValidationRequestMessage.userProfileMessage

        val validationTypes = mutableListOf<ValidationType>()
        userProfileValidationRequestMessage.products
            .forEach { product ->
                validationTypes.add(productToValidationTypeMap[product]!!)
            }
        // Adding Global Validation always irrespective of product exists or not.
        validationTypes.add(ValidationType.GLOBAL)

        // List to store the validation results
        val totalResults = mutableListOf<ValidationResult>()

        /*
         TODO: Validation calls are independent of each other.
          These can be parallelized for optimisation purpose.
         */
        // Get result as per each validation type
        validationTypes.forEach { validationType ->
            val result = validationStrategyRetriever.getValidationStrategy(validationType)
                .validate(userId, userProfileMessage)
            totalResults.add(result)
        }

        // Get failed results
        val failedResults =
            totalResults.filter {
                it.decision == ValidationDecision.FAILED
            }

        /*
        Check if any failed result exists.
        If failed result exists then reject the request else accept.
         */
        val userProfileValidationConfirmationMessage =
            if (failedResults.isNotEmpty()) {
                    UserProfileValidationConfirmationMessage(
                        userId = userId,
                        requestId = requestId,
                        timestamp = Timestamp.from(Instant.now()),
                        state = State.REJECTED,
                        failureReason = "Profile Validation Failed due to $failedResults"
                    )
            } else {
                UserProfileValidationConfirmationMessage(
                    userId = userId,
                    requestId = requestId,
                    timestamp = Timestamp.from(Instant.now()),
                    state = State.ACCEPTED,
                    failureReason = null
                )
            }

        // TODO: Persist the results in DB

        profileValidatorPublisher.publishProfileRequestMessage(userProfileValidationConfirmationMessage)
    }
}