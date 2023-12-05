package demo.craft.user.profile.workflow.dao.impl

import demo.craft.user.profile.workflow.dao.UserProfileWorkflowAccess
import demo.craft.user.profile.workflow.entity.UserProfileWorkflow
import org.springframework.stereotype.Component

@Component
class UserProfileWorkflowAccessDbImpl : UserProfileWorkflowAccess {
    override fun findByUserIdAndRequestId(userId: String, requestId: String): UserProfileWorkflow? {
        TODO("Not yet implemented")
    }
}