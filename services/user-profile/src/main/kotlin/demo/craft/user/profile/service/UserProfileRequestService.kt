package demo.craft.user.profile.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.State
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.user.profile.common.cache.GenericCacheManager
import demo.craft.user.profile.common.exception.*
import demo.craft.user.profile.common.lock.UserProfileLockManager
import demo.craft.user.profile.communication.publisher.UserProfilePublisher
import demo.craft.user.profile.dao.UserProfileAccess
import demo.craft.user.profile.dao.toUserProfile
import demo.craft.user.profile.domain.entity.UserProfile
import demo.craft.user.profile.domain.entity.UserProfileRequest
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class UserProfileRequestService(
    private val userProfileAccess: UserProfileAccess,
    private val userProfilePublisher: UserProfilePublisher,
    private val userProfileLockManager: UserProfileLockManager,
    private val cacheManager: GenericCacheManager
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    @Transactional
    fun triggerCreateUserProfileRequest(
        userId: String,
        userProfileMessage: UserProfileMessage,
    ): UserProfileRequest {
        log.debug { "Received request in [User-Profile] Controller to create business profile." }
        return userProfileLockManager.doExclusively(userId) {
            // Assert user-profile in request
            assertUserProfileShouldNotExistForUser(userId)
            assertNoRequestInProgressExistsForUser(userId)

            val requestId = UUID.randomUUID().toString()
            log.debug { "Request-id: $requestId generated for create request" }
            val userProfileRequest = UserProfileRequest(
                requestId = requestId,
                userId = userId,
                operation = Operation.CREATE,
                state = State.IN_PROGRESS,
                newValue = objectMapper.writeValueAsString(userProfileMessage)
            )
            userProfileAccess.createUserProfileRequest(userProfileRequest)
                .also { userProfileRequest ->
                    // Publish trigger message
                    userProfilePublisher.publishProfileRequestMessage(userProfileRequest)
                }
            return@doExclusively userProfileRequest
        }
    }

    fun getUserProfileRequestDetails(
        userId: String,
        requestId: String
    ): UserProfileRequest {
        return userProfileAccess.findUserProfileRequestByUserIdAndRequestId(userId, requestId)
            ?: throw UserProfileRequestNotFoundException(requestId)
    }

    @Transactional
    fun triggerUpdateUserProfileRequest(
        userId: String,
        userProfileMessage: UserProfileMessage,
    ): UserProfileRequest {
        log.debug { "Received request in [User-Profile] Controller to update business profile." }

        return userProfileLockManager.doExclusively(userId) {
            // Assert user-profile in request
            assertUserProfileShouldExistForUser(userId)
            assertNoRequestInProgressExistsForUser(userId)
            assertUpdateRequestIsNotSameAsExistingForUser(userId, userProfileMessage.toUserProfile(userId))

            val requestId = UUID.randomUUID().toString()
            log.debug { "Request-id: $requestId generated for update request" }
            val userProfileRequest = UserProfileRequest(
                requestId = requestId,
                userId = userId,
                operation = Operation.UPDATE,
                state = State.IN_PROGRESS,
                newValue = objectMapper.writeValueAsString(userProfileMessage)
            )
            userProfileAccess.createUserProfileRequest(userProfileRequest)
                .also { userProfileRequest ->
                    // Publish trigger message
                    userProfilePublisher.publishProfileRequestMessage(userProfileRequest)
                }
            return@doExclusively userProfileRequest
        }
    }

    @Transactional
    fun commitUserProfileRequest(userId: String, requestId: String, state: State) {
        userProfileLockManager.doExclusively(userId) {
            // Update user-profile-request
            val userProfileRequest =
                userProfileAccess.updateUserProfileRequest(
                    userId = userId,
                    requestId = requestId,
                    state = state
                )

            // If request state is ACCEPTED then commit the request to user-profile
            if (userProfileRequest.state == State.ACCEPTED) {
                val userProfile = userProfileAccess.createOrUpdateUserProfile(userProfileRequest)
                log.info { "User profile is successfully committed for request-id: $requestId." }

                // If it is an update then update the cache with the latest value
                if (userProfileRequest.operation == Operation.UPDATE) {
                    cacheManager.update(
                        key = userId,
                        valueTypeRef = object : TypeReference<UserProfile>(){},
                        cacheValue = userProfile
                    )
                }
            } else {
                log.info {
                    "User profile request is not accepted for request-id: $requestId.. Not committing it to user-profile."
                }
            }
        }
    }

    private fun assertNoRequestInProgressExistsForUser(userId: String) {
        userProfileAccess.findUserProfileRequestByUserId(userId)
            ?.filter { it.state == State.IN_PROGRESS }
            ?.let {
                if (it.isNotEmpty()) {
                    throw UserProfileRequestAlreadyInProgressException()
                }
            }
    }

    private fun assertUserProfileShouldExistForUser(userId: String) {
        userProfileAccess.findUserProfileByUserId(userId)
            ?: let {
                throw UserProfileNotFoundException()
            }
    }

    private fun assertUserProfileShouldNotExistForUser(userId: String) {
        userProfileAccess.findUserProfileByUserId(userId)
            ?. let {
                throw UserProfileAlreadyExistsException()
            }
    }

    private fun assertUpdateRequestIsNotSameAsExistingForUser(
        userId: String,
        newUserProfile: UserProfile
    ) {
        val currentUserProfile = userProfileAccess.findUserProfileByUserId(userId)
        if (newUserProfile == currentUserProfile) {
            throw DuplicateUserProfileException()
        }
    }
}
