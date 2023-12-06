package demo.craft.user.profile.common.exception

open class UserProfileException(
    override val message: String,
) : RuntimeException(message)

class UserProfileNotFoundException(userId: String) :
    UserProfileException("User Profile not found for userId: $userId")

class UserProfileRequestNotFoundException(userId: String, requestId: String) :
    UserProfileException("User Profile Request not found for userId: $userId, requestId: $requestId")

class UserProfileRequestAlreadyInProgressException(userId: String, currentRequestId: String) :
    UserProfileException("User Profile Request already in progress for userId: $userId and current request-id: $currentRequestId")

class UserProfileAlreadyExistsException(userId: String) :
    UserProfileException("User Profile already exists for userId: $userId")