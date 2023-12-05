package demo.craft.user.profile.dao.impl.repository

import demo.craft.user.profile.domain.entity.UserProfileHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserProfileHistoryRepository : JpaRepository<UserProfileHistory, Long> {
    fun findAllByUserIdOrderByIdAsc(userId: String): List<UserProfileHistory>
}