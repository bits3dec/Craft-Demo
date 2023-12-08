package demo.craft.profile.validator.service.strategy.impl

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.product.integration.QuickBooksPayrollService
import demo.craft.profile.validator.service.strategy.ValidationStrategy

/**
 * This is "QuickBooks Payroll" product level validation.
 * Here, [QuickBooksPayrollService] service decides the validation.
 *
 * Algorithm:
 * This delegates the validation to the specific product service which is abstract to Profile Validator Service.
 */
class QBPayrollValidationStrategy(
    private val quickBooksPayrollService: QuickBooksPayrollService
) : ValidationStrategy {

    override fun validate(userId: String, userProfileMessage: UserProfileMessage): ValidationResult =
        quickBooksPayrollService.validateUserProfile(userId, userProfileMessage)
}