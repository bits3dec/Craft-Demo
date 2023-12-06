package demo.craft.user.profile.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class UserProfileHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: String,

    // Version number of the "user profile"
    val userProfileVersion: Long,

    // Json string value of the "user profile"
    val value: String,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
)