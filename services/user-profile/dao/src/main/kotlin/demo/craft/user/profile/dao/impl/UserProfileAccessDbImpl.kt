package demo.craft.user.profile.dao.impl

import demo.craft.user.profile.dao.UserProfileAccess
import demo.craft.user.profile.dao.impl.repository.UserProfileRepository
import demo.craft.user.profile.dao.impl.repository.UserProfileRequestRepository
import demo.craft.user.profile.domain.entity.UserProfile
import demo.craft.user.profile.domain.entity.UserProfileRequest
import org.springframework.stereotype.Component

@Component
class UserProfileAccessDbImpl(
    private val userProfileRepository: UserProfileRepository,
    private val userProfileRequestRepository: UserProfileRequestRepository
) : UserProfileAccess {
    override fun findUserProfileByUserId(userId: String): UserProfile? {
        return userProfileRepository.findByUserId(userId)
    }

    override fun createOrUpdateUserProfile(userProfileRequest: UserProfileRequest): UserProfileRequest {
        // TODO: Use the profile request to CREATE or UPDATE in original "user_profile"
        TODO("Not yet implemented")
    }

    override fun createUserProfileRequest(userProfileRequest: UserProfileRequest): UserProfileRequest {
        return userProfileRequestRepository.saveAndFlush(userProfileRequest)
    }

    override fun findUserProfileRequestByUserIdAndRequestId(userId: String, requestId: String): UserProfileRequest? {
        return userProfileRequestRepository.findByUserIdAndRequestId(userId, requestId)
    }
}