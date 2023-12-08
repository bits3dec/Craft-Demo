package demo.craft.profile.validator.service.strategy.impl

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.service.strategy.partner.integration.QuickBooksAccountingService
import demo.craft.profile.validator.service.strategy.ValidationStrategy

/**
 * This is "QuickBooks Accounting" product level validation.
 * Here, [QuickBooksAccountingService] service decides the validation.
 *
 * Algorithm:
 * This delegates the validation to the specific product service which is abstract to Profile Validator Service.
 */
class QBAccountingProfileValidationStrategy(
    private val quickBooksAccountingService: QuickBooksAccountingService
) : ValidationStrategy {

    override fun validate(userId: String, userProfileMessage: UserProfileMessage): ValidationResult =
        quickBooksAccountingService.validateUserProfile(userId, userProfileMessage)
}