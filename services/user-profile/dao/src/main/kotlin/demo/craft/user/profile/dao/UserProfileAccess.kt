package demo.craft.user.profile.dao

import demo.craft.user.profile.domain.entity.UserProfile
import demo.craft.user.profile.domain.entity.UserProfileRequest

interface UserProfileAccess {
    fun findUserProfileByUserId(userId: String): UserProfile?

    fun createOrUpdateUserProfile(userProfileRequest: UserProfileRequest): UserProfileRequest

    fun createUserProfileRequest(userProfileRequest: UserProfileRequest): UserProfileRequest

    fun findUserProfileRequestByUserIdAndRequestId(userId: String, requestId: String): UserProfileRequest?

}