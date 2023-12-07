package demo.craft.profile.validator.product.integration

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult

/**
 * This acts an abstraction between Profile Validator Service and the Product Service to achieve de-coupling
 * like domain models of product is not shared across this boundary.
 */
interface QuickBooksPayrollService {
    fun validateUserProfile(userId: String, userProfileMessage: UserProfileMessage): ValidationResult
}