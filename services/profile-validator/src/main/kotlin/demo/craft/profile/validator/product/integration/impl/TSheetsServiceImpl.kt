package demo.craft.profile.validator.product.integration.impl

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationDecision
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.product.integration.TSheetsService
import org.springframework.stereotype.Component

@Component
class TSheetsServiceImpl : TSheetsService {

    /*
    TODO: This will ideally be an actual API call to the product service.
     However, for demo purpose we are currently mocking the result.
     */
    override fun validateUserProfile(userId: String, userProfileMessage: UserProfileMessage): ValidationResult {
        // Always successful
        return ValidationResult(
            decision = ValidationDecision.SUCCESSFUL,
            failureReason = null
        )
    }
}