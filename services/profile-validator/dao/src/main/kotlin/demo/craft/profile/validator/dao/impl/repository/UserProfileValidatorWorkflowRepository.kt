package demo.craft.profile.validator.dao.impl.repository

import demo.craft.profile.validator.entity.UserProfileValidatorWorkflow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserProfileValidatorWorkflowRepository : JpaRepository<UserProfileValidatorWorkflow, Long> {
    fun findAllByUserIdAndRequestId(userId: String, requestId: String): List<UserProfileValidatorWorkflow>
}