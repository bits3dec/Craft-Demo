package demo.craft.user.profile.workflow.common.exception

open class UserProfileWorkflowException(
    override val message: String,
) : RuntimeException(message)

class UserProfileWorkflowNotFoundException(userId: String, requestId: String) :
    UserProfileWorkflowException("User Profile Workflow not found for userId: $userId, requestId: $requestId")

class UserProfileWorkflowAlreadyExistsException(userId: String, requestId: String) :
    UserProfileWorkflowException("User Profile Workflow not found for userId: $userId, requestId: $requestId")

class DuplicateUserProfileWorkflowStateException(userId: String, requestId: String) :
    UserProfileWorkflowException("Duplicate user profile workflow state for userId: $userId and current request-id: $requestId")