package demo.craft.profile.validator.dao.impl.repository

import demo.craft.profile.validator.entity.FailureReason
import demo.craft.profile.validator.entity.UserProfileValidatorWorkflow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FailureReasonRepository : JpaRepository<FailureReason, Long> {
    fun findAllByUserProfileValidatorWorkflow(userProfileValidatorWorkflow: UserProfileValidatorWorkflow): List<FailureReason>
}

