package demo.craft.profile.validator.service.strategy.impl

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.product.integration.QuickBooksPaymentsService
import demo.craft.profile.validator.service.strategy.ValidationStrategy

/**
 * This is "QuickBooks Payments" product level validation.
 * Here, [QuickBooksPaymentsService] service decides the validation.
 *
 * Algorithm:
 * This delegates the validation to the specific product service which is abstract to Profile Validator Service.
 */
class QBPaymentsProfileValidationStrategy(
    private val quickBooksPaymentsService: QuickBooksPaymentsService
) : ValidationStrategy {

    override fun validate(userId: String, userProfileMessage: UserProfileMessage): ValidationResult =
        quickBooksPaymentsService.validateUserProfile(userId, userProfileMessage)
}