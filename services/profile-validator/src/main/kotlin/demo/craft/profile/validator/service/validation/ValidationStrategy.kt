package demo.craft.profile.validator.service.validation

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationResult

/**
 * This is a contract for all validation strategies.
 * Client is not made aware of the internal implementation details. Hence, making it de-coupled.
 */
interface ValidationStrategy {
    fun validate(userId: String, userProfileMessage: UserProfileMessage): ValidationResult
}