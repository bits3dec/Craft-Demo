package demo.craft.profile.validator.product.integration.impl

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationDecision
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.product.integration.QuickBooksPaymentsService
import org.springframework.stereotype.Component

@Component
class QuickBooksPaymentsImpl : QuickBooksPaymentsService {

    /*
    TODO: This will ideally be an actual API call to the product service.
     However, for demo purpose we are currently mocking the result.
     */
    override fun validateUserProfile(userId: String, userProfileMessage: UserProfileMessage): ValidationResult {
        // Mocking a product rule here which says "Company Name cannot start with Payments".
        if (userProfileMessage.companyName.startsWith("Payments")) {
            // Fail
            return ValidationResult(
                decision = ValidationDecision.FAILED,
                failureReason = "Company Name: ${userProfileMessage.companyName} cannot start with Payments."
            )
        }

        // Else successful
        return ValidationResult(
            decision = ValidationDecision.SUCCESSFULL,
            failureReason = null
        )
    }
}