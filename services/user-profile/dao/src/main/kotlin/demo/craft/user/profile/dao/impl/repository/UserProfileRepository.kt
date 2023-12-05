package demo.craft.user.profile.dao.impl.repository

import demo.craft.user.profile.domain.entity.UserProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserProfileRepository : JpaRepository<UserProfile, Long> {
    fun findByUserId(userId: String): UserProfile?
}