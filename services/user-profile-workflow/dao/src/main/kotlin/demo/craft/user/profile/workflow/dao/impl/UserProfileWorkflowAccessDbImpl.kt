package demo.craft.user.profile.workflow.dao.impl

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.user.profile.workflow.dao.UserProfileWorkflowAccess
import demo.craft.user.profile.workflow.dao.impl.repository.UserProfileWorkflowFailureReasonRepository
import demo.craft.user.profile.workflow.dao.impl.repository.UserProfileWorkflowHistoryRepository
import demo.craft.user.profile.workflow.dao.impl.repository.UserProfileWorkflowRepository
import demo.craft.user.profile.workflow.entity.UserProfileWorkflow
import demo.craft.user.profile.workflow.entity.UserProfileWorkflowFailureReason
import demo.craft.user.profile.workflow.entity.UserProfileWorkflowHistory
import demo.craft.user.profile.workflow.entity.enums.WorkflowState
import org.springframework.stereotype.Component

@Component
class UserProfileWorkflowAccessDbImpl(
    private val userProfileWorkflowRepository: UserProfileWorkflowRepository,
    private val userProfileWorkflowHistoryRepository: UserProfileWorkflowHistoryRepository,
    private val userProfileWorkflowFailureReasonRepository: UserProfileWorkflowFailureReasonRepository
) : UserProfileWorkflowAccess {

    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    override fun findByUserIdAndRequestId(userId: String, requestId: String): UserProfileWorkflow? {
        return userProfileWorkflowRepository.findByUserIdAndRequestIdOrderByIdAsc(userId, requestId)
    }

    override fun createOrUpdateUserProfileWork(
        userProfileWorkflow: UserProfileWorkflow,
        failureReason: String?
    ): UserProfileWorkflow {
        val userProfileWorkflow = userProfileWorkflowRepository.save(userProfileWorkflow)
        userProfileWorkflowHistoryRepository.save(
            UserProfileWorkflowHistory(
                userProfileWorkflow = userProfileWorkflow,
                value = objectMapper.writeValueAsString(userProfileWorkflow)
            )
        )

        // Save failure reason in case of the workflow state is failure
        if (userProfileWorkflow.state == WorkflowState.FAILURE) {
            userProfileWorkflowFailureReasonRepository.save(
                UserProfileWorkflowFailureReason(
                    userProfileWorkflow = userProfileWorkflow,
                    reason = failureReason ?: "NA"
                )
            )
        }

        return userProfileWorkflow
    }

    override fun findUserProfileWorkflowHistoryByUserIdAndRequestId(
        userId: String,
        requestId: String
    ): List<UserProfileWorkflow>? {
        return findByUserIdAndRequestId(userId, requestId)
            ?.let {
                userProfileWorkflowHistoryRepository.findAllByUserProfileWorkflow(it)
                    .map { userProfileWorkflow ->
                        objectMapper.readValue(userProfileWorkflow.value, UserProfileWorkflow::class.java)
                    }
            }
    }

    override fun findFailureReasonByUserIdAndRequestId(
        userId: String,
        requestId: String
    ): UserProfileWorkflowFailureReason? {
        return findByUserIdAndRequestId(userId, requestId)
            ?.let {
                userProfileWorkflowFailureReasonRepository.findByUserProfileWorkflow(it)
            }
    }
}