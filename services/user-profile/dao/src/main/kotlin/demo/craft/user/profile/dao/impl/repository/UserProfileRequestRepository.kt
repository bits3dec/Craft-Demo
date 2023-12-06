package demo.craft.user.profile.dao.impl.repository

import demo.craft.user.profile.domain.entity.UserProfileRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserProfileRequestRepository : JpaRepository<UserProfileRequest, Long> {
    fun findByUserIdAndRequestId(userId: String, requestId: String): UserProfileRequest?
    fun findByUserId(userId: String): List<UserProfileRequest>?
}