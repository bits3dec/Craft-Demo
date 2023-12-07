package demo.craft.profile.validator.service.validation.impl

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.product.integration.QuickBooksAccountingService
import demo.craft.profile.validator.product.integration.QuickBooksPaymentsService
import demo.craft.profile.validator.service.validation.ValidationStrategy

/**
 * This is "QuickBooks Accounting" product level validation.
 * Here, [QuickBooksAccountingService] service decides the validation.
 */
class QBAccountingProfileValidationStrategy(
    private val quickBooksAccountingService: QuickBooksAccountingService
) : ValidationStrategy {

    /**
     * Algorithm:
     * This delegates the validation to the specific product.
     */
    override fun validate(userId: String, userProfileMessage: UserProfileMessage): ValidationResult =
        quickBooksAccountingService.validateUserProfile(userId, userProfileMessage)
}