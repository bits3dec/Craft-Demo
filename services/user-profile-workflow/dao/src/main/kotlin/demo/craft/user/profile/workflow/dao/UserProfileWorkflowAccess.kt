package demo.craft.user.profile.workflow.dao

import demo.craft.user.profile.workflow.entity.UserProfileWorkflow
import demo.craft.user.profile.workflow.entity.UserProfileWorkflowFailureReason

interface UserProfileWorkflowAccess {
    fun findByUserIdAndRequestId(userId: String, requestId: String): UserProfileWorkflow?

    fun createOrUpdateUserProfileWork(
        userProfileWorkflow: UserProfileWorkflow,
        failureReason: String?
    ): UserProfileWorkflow

    fun findUserProfileWorkflowHistoryByUserIdAndRequestId(userId: String, requestId: String): List<UserProfileWorkflow>?
    fun findFailureReasonByUserIdAndRequestId(userId: String, requestId: String): UserProfileWorkflowFailureReason?
}