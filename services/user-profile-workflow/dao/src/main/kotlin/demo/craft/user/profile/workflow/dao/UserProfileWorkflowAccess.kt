package demo.craft.user.profile.workflow.dao

import demo.craft.user.profile.workflow.entity.UserProfileWorkflow

interface UserProfileWorkflowAccess {
    fun findByUserIdAndRequestId(userId: String, requestId: String): UserProfileWorkflow?
}