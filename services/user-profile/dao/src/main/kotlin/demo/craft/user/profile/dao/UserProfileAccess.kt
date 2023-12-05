package demo.craft.user.profile.dao

import demo.craft.user.profile.domain.entity.UserProfile
import demo.craft.user.profile.domain.entity.UserProfileRequest

interface UserProfileAccess {
    fun findByUserId(userId: String): UserProfile?

    fun createOrUpdateUserProfile(changeRequest: UserProfileRequest): UserProfile
}