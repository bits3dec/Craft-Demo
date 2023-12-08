package demo.craft.profile.validator

data class ValidationResult(
    val decision: ValidationDecision,
    val failureReason: String? // Set the failure reason in case decision is FAILURE
)
