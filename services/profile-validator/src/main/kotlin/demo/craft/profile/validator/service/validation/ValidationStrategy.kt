package demo.craft.profile.validator.service.validation

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult

/**
 * This is a contract for all validation strategies.
 */
interface ValidationStrategy {
    fun validate(userId: String, userProfileMessage: UserProfileMessage): ValidationResult
}