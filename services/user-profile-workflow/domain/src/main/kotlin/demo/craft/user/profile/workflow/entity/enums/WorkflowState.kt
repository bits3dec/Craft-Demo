package demo.craft.user.profile.workflow.entity.enums

enum class WorkflowState {
    INITIATED,
    PROFILE_VALIDATION_INITIATED,
    PROFILE_VALIDATION_SUCCESSFUL,
    PROFILE_VALIDATION_FAILED,
    ACCEPTED,
    FAILURE
}