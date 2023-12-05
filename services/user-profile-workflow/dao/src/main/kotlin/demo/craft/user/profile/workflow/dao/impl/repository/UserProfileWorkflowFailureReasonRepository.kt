package demo.craft.user.profile.workflow.dao.impl.repository

import demo.craft.user.profile.workflow.entity.UserProfileWorkflow
import demo.craft.user.profile.workflow.entity.UserProfileWorkflowFailureReason
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserProfileWorkflowFailureReasonRepository : JpaRepository<UserProfileWorkflowFailureReason, Long> {
    fun findByUserProfileWorkflow(userProfileWorkflow: UserProfileWorkflow): UserProfileWorkflowFailureReason?
}