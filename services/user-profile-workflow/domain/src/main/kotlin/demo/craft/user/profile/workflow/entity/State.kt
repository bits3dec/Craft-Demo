package demo.craft.user.profile.workflow.entity

enum class State {
    INITIATED,
    PROFILE_VALIDATION_INITIATED,
    PROFILE_VALIDATION_RECEIVED,
    ACCEPTED,
    FAILURE
}