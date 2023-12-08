package demo.craft.profile.validator.service.strategy.impl

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.service.strategy.partner.integration.TSheetsService
import demo.craft.profile.validator.service.strategy.ValidationStrategy

/**
 * This is "TSheets" product level validation.
 * Here, [TSheetsService] service decides the validation.
 *
 * Algorithm:
 * This delegates the validation to the specific product service which is abstract to Profile Validator Service.
 */
class TSheetsProfileValidationStrategy(
    private val tSheetsService: TSheetsService
) : ValidationStrategy {

    /**
     * Algorithm:
     * This delegates the validation to the specific product.
     */
    override fun validate(userId: String, userProfileMessage: UserProfileMessage): ValidationResult =
        tSheetsService.validateUserProfile(userId, userProfileMessage)
}