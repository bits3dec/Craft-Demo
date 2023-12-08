package demo.craft.profile.validator.entity

import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.State
import demo.craft.profile.validator.entity.enums.ValidationType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class UserProfileValidatorWorkflow(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: String,
    val requestId: String,

    // Json string value of the "user profile message"
    val newValue: String,

    @Enumerated(EnumType.STRING)
    val operation: Operation,
    @Enumerated(EnumType.STRING)
    val validationType: ValidationType,
    @Enumerated(EnumType.STRING)
    val state: State,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
)