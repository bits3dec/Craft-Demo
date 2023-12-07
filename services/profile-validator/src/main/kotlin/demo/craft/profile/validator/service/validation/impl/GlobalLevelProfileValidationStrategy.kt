package demo.craft.profile.validator.service.validation.impl

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.profile.validator.ValidationDecision
import demo.craft.profile.validator.ValidationResult
import demo.craft.profile.validator.service.validation.ValidationStrategy

class GlobalLevelProfileValidationStrategy : ValidationStrategy {

    /**
     * Algorithm:
     * This has a list of rules to check globally and decide the result.
     */
    override fun validate(userId: String, userProfileMessage: UserProfileMessage): ValidationResult {
        // Country Rule: List of country which are not allowed.
        if (userProfileMessage.businessAddress.country == "FooBar" ||
            userProfileMessage.legalAddress.country == "FooBar"
        ) {
            // Fail
            return ValidationResult(
                decision = ValidationDecision.FAILED,
                failureReason = "Country: ${userProfileMessage.businessAddress.country} is not serviceable."
            )
        }

        // ...other rules in future

        // Else successful
        return ValidationResult(
            decision = ValidationDecision.SUCCESSFULL,
            failureReason = null
        )
    }
}