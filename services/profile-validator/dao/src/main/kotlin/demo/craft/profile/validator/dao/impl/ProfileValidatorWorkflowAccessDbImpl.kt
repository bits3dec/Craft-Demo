package demo.craft.profile.validator.dao.impl

import demo.craft.profile.validator.dao.ProfileValidatorWorkflowAccess
import demo.craft.profile.validator.dao.impl.repository.FailureReasonRepository
import demo.craft.profile.validator.dao.impl.repository.UserProfileValidatorWorkflowRepository
import demo.craft.profile.validator.entity.FailureReason
import demo.craft.profile.validator.entity.UserProfileValidatorWorkflow
import org.springframework.stereotype.Component

@Component
class ProfileValidatorWorkflowAccessDbImpl(
    private val userProfileValidatorWorkflowRepository: UserProfileValidatorWorkflowRepository,
    private val failureReasonRepository: FailureReasonRepository
) : ProfileValidatorWorkflowAccess {

    override fun createProfileValidatorWorkflow(
        userProfileValidatorWorkflow: UserProfileValidatorWorkflow,
        failureReason: String?
    ): UserProfileValidatorWorkflow {
        val persistedUserProfileValidatorWorkflow =
            userProfileValidatorWorkflowRepository.save(userProfileValidatorWorkflow)
        if (persistedUserProfileValidatorWorkflow.state == demo.craft.common.domain.enums.State.REJECTED) {
            val failureReason =
                FailureReason(
                    userProfileValidatorWorkflow = persistedUserProfileValidatorWorkflow,
                    reason = failureReason ?: "NA"
                )
            failureReasonRepository.save(failureReason)
        }
        return persistedUserProfileValidatorWorkflow
    }

    override fun getAllProfileValidatorWorkflow(userId: String, requestId: String): List<UserProfileValidatorWorkflow> =
        userProfileValidatorWorkflowRepository.findAllByUserIdAndRequestId(userId, requestId)
}