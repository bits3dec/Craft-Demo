package demo.craft.user.profile.domain.entity

import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.State
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class UserProfileRequest(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val requestId: String,
    val userId: String,
    @Enumerated(EnumType.STRING)
    val operation: Operation,
    @Enumerated(EnumType.STRING)
    val state: State,

    // Json string value of the new "user profile"
    val newValue: String,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
)