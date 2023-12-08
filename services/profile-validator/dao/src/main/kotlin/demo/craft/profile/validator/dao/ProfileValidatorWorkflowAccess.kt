package demo.craft.profile.validator.dao

import demo.craft.profile.validator.entity.UserProfileValidatorWorkflow

interface ProfileValidatorWorkflowAccess {

    fun createProfileValidatorWorkflow(
        userProfileValidatorWorkflow: UserProfileValidatorWorkflow,
        failureReason: String? // Pass failure reason in case state is REJECTED
    ): UserProfileValidatorWorkflow

    fun getAllProfileValidatorWorkflow(userId: String, requestId: String): List<UserProfileValidatorWorkflow>
}