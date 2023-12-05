package demo.craft.user.profile.dao.impl

import demo.craft.user.profile.dao.UserProfileAccess
import demo.craft.user.profile.domain.entity.UserProfile
import demo.craft.user.profile.domain.entity.UserProfileRequest
import org.springframework.stereotype.Component

@Component
class UserProfileAccessDbImpl : UserProfileAccess {
    override fun findByUserId(userId: String): UserProfile? {
        TODO("Not yet implemented")
    }

    override fun createOrUpdateUserProfile(changeRequest: UserProfileRequest): UserProfile {
        TODO("Not yet implemented")
    }
}