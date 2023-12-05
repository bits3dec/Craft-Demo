package demo.craft.user.profile.workflow.dao.impl.repository

import demo.craft.user.profile.workflow.entity.UserProfileWorkflow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserProfileWorkflowRepository : JpaRepository<UserProfileWorkflow, Long> {
    fun findAllByUserIdAndRequestIdOrderByIdAsc(userId: String, requestId: String): List<UserProfileWorkflow>
}