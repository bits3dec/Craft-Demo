package demo.craft.user.profile.dao.impl

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.user.profile.common.exception.UserProfileAlreadyExistsException
import demo.craft.user.profile.common.exception.UserProfileNotFoundException
import demo.craft.user.profile.common.exception.UserProfileRequestNotFoundException
import demo.craft.user.profile.dao.UserProfileAccess
import demo.craft.user.profile.dao.impl.repository.UserProfileHistoryRepository
import demo.craft.user.profile.dao.impl.repository.UserProfileRepository
import demo.craft.user.profile.dao.impl.repository.UserProfileRequestRepository
import demo.craft.user.profile.dao.toAddress
import demo.craft.user.profile.dao.toTaxIdentifier
import demo.craft.user.profile.dao.toUserProfile
import demo.craft.user.profile.domain.entity.UserProfile
import demo.craft.user.profile.domain.entity.UserProfileHistory
import demo.craft.user.profile.domain.entity.UserProfileRequest
import org.springframework.stereotype.Component

@Component
class UserProfileAccessDbImpl(
    private val userProfileRepository: UserProfileRepository,
    private val userProfileRequestRepository: UserProfileRequestRepository,
    private val userProfileHistoryRepository: UserProfileHistoryRepository
) : UserProfileAccess {

    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }


    override fun findUserProfileByUserId(userId: String): UserProfile? {
        return userProfileRepository.findByUserId(userId)
    }

    /*
    1. User Profile
        Do an Upsert operation in "user-profile".
         1.1 Update: If entry exists then update.
         2.1 Insert: If entry does not exist then insert an entry
     2. User Profile History
        Create an entry in 'user-profile-history".
     */
    override fun createOrUpdateUserProfile(userProfileRequest: UserProfileRequest): UserProfile {
        val userProfileMessage = objectMapper.readValue(userProfileRequest.newValue, UserProfileMessage::class.java)
        return findUserProfileByUserId(userProfileRequest.userId)
            ?.let { userProfile ->
                // If operation is CREATE then it is expected that there is not user-profile present.
                if (userProfileRequest.operation == Operation.CREATE) {
                    throw UserProfileAlreadyExistsException(userProfileRequest.userId)
                }

                // User Profile exists so update the existing one.
                val updatedUserProfile = userProfile.copy(
                    companyName = userProfileMessage.companyName,
                    legalName = userProfileMessage.legalName,
                    businessAddress = userProfileMessage.businessAddress.toAddress(),
                    legalAddress = userProfileMessage.legalAddress.toAddress(),
                    taxIdentifier = userProfileMessage.taxIdentifier.toTaxIdentifier(),
                    email = userProfileMessage.email,
                    website = userProfileMessage.website
                )

                // Save in "user-profile"
                val savedUserProfile = userProfileRepository.saveAndFlush(updatedUserProfile)

                // Save in "user-profile-history"
                val newVersion =
                    userProfileHistoryRepository.findAllByUserIdOrderByIdAsc(userProfileRequest.userId).last()
                        .userProfileVersion
                        .plus(1)
                val userProfileHistory =
                    UserProfileHistory(
                        userId = userProfileRequest.userId,
                        userProfileVersion = newVersion,
                        value = objectMapper.writeValueAsString(savedUserProfile),
                    )
                userProfileHistoryRepository.saveAndFlush(userProfileHistory)
                savedUserProfile
            }
            ?: let {
                // If operation is UPDATE then it is expected that a user-profile exists.
                if (userProfileRequest.operation == Operation.UPDATE) {
                    throw UserProfileNotFoundException(userProfileRequest.userId)
                }

                val newUserProfile = userProfileMessage.toUserProfile(userProfileRequest.userId)
                val savedUserProfile = userProfileRepository.saveAndFlush(newUserProfile)

                val userProfileHistory =
                    UserProfileHistory(
                        userId = userProfileRequest.userId,
                        userProfileVersion = 1, // Version is set to 1 as it is the first entry
                        value = objectMapper.writeValueAsString(savedUserProfile),
                    )
                userProfileHistoryRepository.saveAndFlush(userProfileHistory)

                savedUserProfile
            }
    }

    override fun createUserProfileRequest(userProfileRequest: UserProfileRequest): UserProfileRequest {
        return userProfileRequestRepository.saveAndFlush(userProfileRequest)
    }

    override fun updateUserProfileRequest(userProfileRequest: UserProfileRequest): UserProfileRequest {
        return userProfileRequestRepository
            .findByUserIdAndRequestId(
                userId = userProfileRequest.userId,
                requestId = userProfileRequest.requestId
            )
            ?.let { currentUserProfileRequest ->
                userProfileRequestRepository.saveAndFlush(currentUserProfileRequest.copy(state = userProfileRequest.state))
            } ?: let {
                throw UserProfileRequestNotFoundException(
                    userId = userProfileRequest.userId,
                    requestId = userProfileRequest.requestId
                )
            }
    }

    override fun findUserProfileRequestByUserIdAndRequestId(userId: String, requestId: String): UserProfileRequest? {
        return userProfileRequestRepository.findByUserIdAndRequestId(userId, requestId)
    }

    override fun findUserProfileRequestByUserId(userId: String): UserProfileRequest? {
        return userProfileRequestRepository.findByUserId(userId)
    }


}