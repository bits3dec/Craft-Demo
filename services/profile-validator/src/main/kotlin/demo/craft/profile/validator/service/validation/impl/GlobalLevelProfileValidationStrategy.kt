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
        // Rule#1: Business Address Country Rule checks country or list of country which are not allowed.
        if (userProfileMessage.businessAddress.country == "FooBar") {
            // Fail
            return ValidationResult(
                decision = ValidationDecision.FAILED,
                failureReason = "Country: ${userProfileMessage.businessAddress.country} is not serviceable."
            )
        }

        // Rule#1: Legal Address Country Rule checks country or list of country which are not allowed.
        if (userProfileMessage.legalAddress.country == "FooBar") {
            // Fail
            return ValidationResult(
                decision = ValidationDecision.FAILED,
                failureReason = "Country: ${userProfileMessage.legalAddress.country} is not serviceable."
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