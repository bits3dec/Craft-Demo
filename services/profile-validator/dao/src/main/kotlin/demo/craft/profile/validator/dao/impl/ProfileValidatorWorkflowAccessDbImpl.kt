package demo.craft.profile.validator.dao.impl

import demo.craft.profile.validator.dao.ProfileValidatorWorkflowAccess
import demo.craft.profile.validator.dao.impl.repository.FailureReasonRepository
import demo.craft.profile.validator.dao.impl.repository.UserProfileValidatorWorkflowRepository
import demo.craft.profile.validator.entity.UserProfileValidatorWorkflow
import org.springframework.stereotype.Component

@Component
class ProfileValidatorWorkflowAccessDbImpl(
    private val userProfileValidatorWorkflowRepository: UserProfileValidatorWorkflowRepository,
    private val failureReasonRepository: FailureReasonRepository
) : ProfileValidatorWorkflowAccess {

    override fun createProfileValidatorWorkflow(userId: String, requestId: String): UserProfileValidatorWorkflow {
        TODO("Not yet implemented")
    }

    override fun getAllProfileValidatorWorkflow(userId: String, requestId: String): List<UserProfileValidatorWorkflow> {
        TODO("Not yet implemented")
    }
}