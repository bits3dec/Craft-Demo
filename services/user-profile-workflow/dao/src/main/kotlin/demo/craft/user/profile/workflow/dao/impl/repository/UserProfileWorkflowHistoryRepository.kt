package demo.craft.user.profile.workflow.dao.impl.repository

import demo.craft.user.profile.workflow.entity.UserProfileWorkflow
import demo.craft.user.profile.workflow.entity.UserProfileWorkflowHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserProfileWorkflowHistoryRepository : JpaRepository<UserProfileWorkflowHistory, Long> {
    fun findAllByUserProfileWorkflow(userProfileWorkflow: UserProfileWorkflow): List<UserProfileWorkflowHistory>
}