package demo.craft.profile.validator.service.validation.impl

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.product.integration.QuickBooksAccountingService
import demo.craft.profile.validator.product.integration.QuickBooksPaymentsService
import demo.craft.profile.validator.product.integration.QuickBooksPayrollService
import demo.craft.profile.validator.service.validation.ValidationStrategy

/**
 * This is "QuickBooks Payments" product level validation.
 * Here, [QuickBooksPaymentsService] service decides the validation.
 */
class QBPaymentsProfileValidationStrategy(
    private val quickBooksPaymentsService: QuickBooksPaymentsService
) : ValidationStrategy {

    /**
     * Algorithm:
     * This delegates the validation to the specific product.
     */
    override fun validate(userId: String, userProfileMessage: UserProfileMessage): ValidationResult =
        quickBooksPaymentsService.validateUserProfile(userId, userProfileMessage)
}