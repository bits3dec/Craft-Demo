package demo.craft.user.profile.workflow.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.State
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.user.profile.workflow.common.exception.UserProfileWorkflowAlreadyExistsException
import demo.craft.user.profile.workflow.common.lock.UserProfileWorkflowLockManager
import demo.craft.user.profile.workflow.communication.publisher.UserProfileWorkflowPublisher
import demo.craft.user.profile.workflow.dao.UserProfileWorkflowAccess
import demo.craft.user.profile.workflow.entity.UserProfileWorkflow
import demo.craft.user.profile.workflow.entity.enums.WorkflowState
import demo.craft.user.profile.workflow.integration.ProductRegistryService
import org.springframework.stereotype.Component
import javax.transaction.NotSupportedException
import javax.transaction.Transactional

@Component
class UserProfileWorkflowService(
    private val userProfileWorkflowAccess: UserProfileWorkflowAccess,
    private val productRegistryService: ProductRegistryService,
    private val userProfileWorkflowPublisher: UserProfileWorkflowPublisher,
    private val userProfileWorkflowLockManager: UserProfileWorkflowLockManager
) {

    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    private val terminalStates = listOf(WorkflowState.ACCEPTED, WorkflowState.FAILURE)

    @Transactional
    fun createUserProfileWork(
        userId: String,
        requestId: String,
        operation: Operation,
        userProfileMessage: UserProfileMessage,
    ): UserProfileWorkflow {
        return userProfileWorkflowLockManager.doExclusively(userId) {
            userProfileWorkflowAccess.findByUserIdAndRequestId(userId, requestId)
                ?.let {
                    throw UserProfileWorkflowAlreadyExistsException(userId, requestId)
                }

            val userProfileWorkflow =
                UserProfileWorkflow(
                    userId = userId,
                    requestId = requestId,
                    operation = operation,
                    newValue = objectMapper.writeValueAsString(userProfileMessage),
                    state = WorkflowState.INITIATED
                )

            userProfileWorkflowAccess.createOrUpdateUserProfileWork(userProfileWorkflow, failureReason = null)
                .also {
                    val activeProductsForUser = productRegistryService.getAllSubscribedProductsForUser(userId)
                    // Publish to kafka to start profile validation
                    userProfileWorkflowPublisher.publishProfileValidationRequestMessage(it, activeProductsForUser)
                }

            return@doExclusively userProfileWorkflowAccess.createOrUpdateUserProfileWork(userProfileWorkflow.copy(state = WorkflowState.PROFILE_VALIDATION_INITIATED), failureReason = null)
        }
    }

    fun updateUserProfileWork(
        userId: String,
        requestId: String,
        state: State,
        failureReason: String?
    ): UserProfileWorkflow {
        return userProfileWorkflowLockManager.doExclusively(userId) {
            return@doExclusively userProfileWorkflowAccess.findByUserIdAndRequestId(userId, requestId)
                ?.let {
                    updateUserProfileWorkflowStates(it, state, failureReason)
                        .also { userProfileWorkflow ->
                            // Fetch failure reason if state is FAILURE
                            val userProfileWorkflowFailureReason =
                                if (userProfileWorkflow.state == WorkflowState.FAILURE) {
                                    userProfileWorkflowAccess.findFailureReasonByUserIdAndRequestId(userId, requestId)
                                } else {
                                    null
                                }
                            if (userProfileWorkflow.state in terminalStates) {
                                // Publish to kafka to confirm profile request operation
                                userProfileWorkflowPublisher.publishProfileRequestConfirmationMessage(
                                    userProfileWorkflow = userProfileWorkflow,
                                    userProfileWorkflowFailureReason = userProfileWorkflowFailureReason
                                )
                            }
                        }
                }
                ?: let {
                    throw UserProfileWorkflowAlreadyExistsException(userId, requestId)
                }
        }
    }

    private fun updateUserProfileWorkflowStates(
        currentUserProfileWorkflow: UserProfileWorkflow,
        state: State,
        failureReason: String?
    ): UserProfileWorkflow {
        /*
         In the context of profile validation the state received is mapped to internal workflow state.
         Currently, update is called only in the context of profile validation so the below code
         assumes context as Profile Validation and does the mapping.
         Later if we have multiple flows triggering the update then we can take the context from the callers
         as method parameter and then do the mapping here as per the context.
          */
        val state =
            when (state) {
                State.ACCEPTED -> WorkflowState.PROFILE_VALIDATION_SUCCESSFUL
                State.REJECTED -> WorkflowState.PROFILE_VALIDATION_FAILED
                else -> {
                    throw NotSupportedException("Not a valid state: $state")
                }
            }

        return userProfileWorkflowAccess.createOrUpdateUserProfileWork(
            currentUserProfileWorkflow.copy(state = state), failureReason = failureReason // Update state as per the request
        ).also { userProfileWorkflow ->
            if (userProfileWorkflow.state == WorkflowState.PROFILE_VALIDATION_SUCCESSFUL) { // Check if the state can be considered as success
                userProfileWorkflowAccess.createOrUpdateUserProfileWork(
                    userProfileWorkflow.copy(state = WorkflowState.ACCEPTED), failureReason = failureReason
                )
            } else if (userProfileWorkflow.state == WorkflowState.PROFILE_VALIDATION_FAILED) { // Check if the state can be considered as failure
                userProfileWorkflowAccess.createOrUpdateUserProfileWork(
                    userProfileWorkflow.copy(state = WorkflowState.FAILURE), failureReason = failureReason
                )
            }
        }
    }

}