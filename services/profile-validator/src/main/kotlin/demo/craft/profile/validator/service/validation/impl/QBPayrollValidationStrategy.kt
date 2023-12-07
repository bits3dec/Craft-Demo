package demo.craft.profile.validator.service.validation.impl

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.product.integration.QuickBooksPayrollService
import demo.craft.profile.validator.product.integration.TSheetsService
import demo.craft.profile.validator.service.validation.ValidationStrategy

/**
 * This is "QuickBooks Payroll" product level validation.
 * Here, [QuickBooksPayrollService] service decides the validation.
 */
class QBPayrollValidationStrategy(
    private val quickBooksPayrollService: QuickBooksPayrollService
) : ValidationStrategy {

    /**
     * Algorithm:
     * This delegates the validation to the specific product.
     */
    override fun validate(userId: String, userProfileMessage: UserProfileMessage): ValidationResult =
        quickBooksPayrollService.validateUserProfile(userId, userProfileMessage)
}