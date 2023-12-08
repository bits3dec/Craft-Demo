package demo.craft.profile.validator.common.exception

open class UserProfileValidatorException(
    override val message: String,
) : RuntimeException(message)

class InvalidUserProfileValidatorStateException(userId: String, requestId: String) :
    UserProfileValidatorException("Invalid user profile validator state for user: $userId, requestId: $requestId")