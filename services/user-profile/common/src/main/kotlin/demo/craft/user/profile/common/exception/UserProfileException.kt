package demo.craft.user.profile.common.exception

open class UserProfileException(
    override val message: String,
) : RuntimeException(message)

class UserProfileNotFoundException :
    UserProfileException("User profile not found")

class UserProfileRequestNotFoundException(requestId: String) :
    UserProfileException("User profile Request not found for requestId: $requestId")

class UserProfileRequestAlreadyInProgressException :
    UserProfileException("User profile Request already in progress")

class UserProfileAlreadyExistsException :
    UserProfileException("User profile already exists")


class InvalidUserProfileRequestException(invalidFields: String) :
    UserProfileException("Invalid User profile request. Invalid Fields: $invalidFields")
