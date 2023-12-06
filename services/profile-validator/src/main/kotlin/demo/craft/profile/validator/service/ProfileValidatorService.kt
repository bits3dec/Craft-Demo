package demo.craft.profile.validator.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.State
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.common.domain.kafka.impl.UserProfileValidationConfirmationMessage
import demo.craft.profile.validator.communication.publisher.ProfileValidatorPublisher
import demo.craft.profile.validator.dao.ProfileValidatorWorkflowAccess
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.Instant

@Component
class ProfileValidatorService(
    private val userProfileValidatorWorkflowAccess: ProfileValidatorWorkflowAccess,
    private val profileValidatorPublisher: ProfileValidatorPublisher
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    fun createUserProfileValidatorWorkflow(
        userId: String,
        requestId: String,
        operation: Operation,
        userProfileMessage: UserProfileMessage,
    ) {
        /*
        TODO: Add Profile Validation Logic.
            Mocking profile validation as "SUCCESSFUL" currently for testing purpose.
         */

        val userProfileValidationConfirmationMessage =
            UserProfileValidationConfirmationMessage(
                userId = userId,
                requestId = requestId,
                timestamp = Timestamp.from(Instant.now()),
                state = State.ACCEPTED,
                failureReason = null
            )
        profileValidatorPublisher.publishProfileRequestMessage(userProfileValidationConfirmationMessage)
    }
}