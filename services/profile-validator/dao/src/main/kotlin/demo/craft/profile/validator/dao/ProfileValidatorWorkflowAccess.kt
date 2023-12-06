package demo.craft.profile.validator.dao

import demo.craft.profile.validator.entity.UserProfileValidatorWorkflow

interface ProfileValidatorWorkflowAccess {

    fun createProfileValidatorWorkflow(userId: String, requestId: String): UserProfileValidatorWorkflow

    fun getAllProfileValidatorWorkflow(userId: String, requestId: String): List<UserProfileValidatorWorkflow>
}