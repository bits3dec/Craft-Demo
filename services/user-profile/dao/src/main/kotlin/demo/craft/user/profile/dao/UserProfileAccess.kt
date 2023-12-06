package demo.craft.user.profile.dao

import demo.craft.common.domain.enums.State
import demo.craft.user.profile.domain.entity.UserProfile
import demo.craft.user.profile.domain.entity.UserProfileRequest

interface UserProfileAccess {
    fun findUserProfileByUserId(userId: String): UserProfile?

    fun createOrUpdateUserProfile(userProfileRequest: UserProfileRequest): UserProfile

    fun createUserProfileRequest(userProfileRequest: UserProfileRequest): UserProfileRequest
    fun updateUserProfileRequest(userId: String, requestId: String, state: State): UserProfileRequest

    fun findUserProfileRequestByUserIdAndRequestId(userId: String, requestId: String): UserProfileRequest?

    fun findUserProfileRequestByUserId(userId: String): List<UserProfileRequest>?

}