package demo.craft.user.profile.service

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.State
import demo.craft.user.profile.common.config.UserProfileProperties
import demo.craft.user.profile.common.exception.UserProfileNotFoundException
import demo.craft.user.profile.common.exception.UserProfileRequestAlreadyInProgressException
import demo.craft.user.profile.common.exception.UserProfileRequestNotFoundException
import demo.craft.user.profile.communication.publisher.UserProfilePublisher
import demo.craft.user.profile.dao.UserProfileAccess
import demo.craft.user.profile.domain.entity.UserProfile
import demo.craft.user.profile.domain.entity.UserProfileRequest
import demo.craft.user.profile.model.*
import demo.craft.user.profile.mapper.toDomainModel
import demo.craft.user.profile.mapper.toUserProfileMessage
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.util.UUID
import javax.transaction.Transactional

@Component
class UserProfileRequestService(
    private val userProfileAccess: UserProfileAccess,
    private val userProfilePublisher: UserProfilePublisher,
    private val userProfileProperties: UserProfileProperties
) {
    private val log = KotlinLogging.logger {}
    private val kafkaProperties = userProfileProperties.kafka
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }

    @Transactional
    fun triggerCreateUserProfileRequest(
        userId: String,
        createBusinessProfileRequest: CreateBusinessProfileRequest
    ): UserProfileRequest {
        log.debug { "Received request in [User-Profile] Controller to create business profile." }

        checkIfAnyInProgressRequestExistsForUser(userId)

        val requestId = UUID.randomUUID().toString()
        log.debug { "Request-id: $requestId generated for create request" }
        val userProfileRequest = UserProfileRequest(
            requestId = requestId,
            userId = userId,
            operation = Operation.CREATE,
            state = State.IN_PROGRESS,
            newValue = objectMapper.writeValueAsString(createBusinessProfileRequest.businessProfile.toUserProfileMessage())
        )
        userProfileAccess.createUserProfileRequest(userProfileRequest)
            .also { userProfileRequest ->
                userProfilePublisher.publishProfileRequestMessage(userProfileRequest)
            }
        return userProfileRequest
    }

    fun getUserProfileRequestDetails(
        userId: String,
        requestId: String
    ): UserProfileRequest {
        return userProfileAccess.findUserProfileRequestByUserIdAndRequestId(userId, requestId)
            ?: throw UserProfileRequestNotFoundException(userId, requestId)
    }

    @Transactional
    fun triggerUpdateUserProfileRequest(
        userId: String,
        updateBusinessProfileRequest: UpdateBusinessProfileRequest
    ): UserProfileRequest {
        log.debug { "Received request in [User-Profile] Controller to update business profile." }

        checkIfAnyUserProfileExistsForUser(userId)
        checkIfAnyInProgressRequestExistsForUser(userId)

        val requestId = UUID.randomUUID().toString()
        log.debug { "Request-id: $requestId generated for update request" }
        val userProfileRequest = UserProfileRequest(
            requestId = requestId,
            userId = userId,
            operation = Operation.UPDATE,
            state = State.IN_PROGRESS,
            newValue = objectMapper.writeValueAsString(updateBusinessProfileRequest.businessProfile.toDomainModel(userId))
        )
        userProfileAccess.createUserProfileRequest(userProfileRequest)
        return userProfileRequest
    }

    @Transactional
    fun commitUserProfileRequest(userProfileRequest: UserProfileRequest): UserProfile {
        // Update user-profile-request
        userProfileAccess.updateUserProfileRequest(userProfileRequest)
        // Create or Update user-profile
        return userProfileAccess.createOrUpdateUserProfile(userProfileRequest)
    }

    private fun checkIfAnyInProgressRequestExistsForUser(userId: String) {
        userProfileAccess.findUserProfileRequestByUserId(userId)
            ?.takeIf { it.state == State.IN_PROGRESS}
            ?.let {
                throw UserProfileRequestAlreadyInProgressException(userId, it.requestId)
            }
    }

    private fun checkIfAnyUserProfileExistsForUser(userId: String) {
        userProfileAccess.findUserProfileByUserId(userId)
            ?: let {
                throw UserProfileNotFoundException(userId)
            }
    }
}
