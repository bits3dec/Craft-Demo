package demo.craft.profile.validator.service.strategy.partner.integration

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult

/**
 * This acts an abstraction between Profile Validator Service and the Product Service to achieve de-coupling between two
 * services.
 * For e.g, having this layer will allow us to ensure domain models of product is not shared across this boundary.
 * Hence, ensuring de-coupling.
 */
interface QuickBooksAccountingService {
    fun validateUserProfile(userId: String, userProfileMessage: UserProfileMessage): ValidationResult
}