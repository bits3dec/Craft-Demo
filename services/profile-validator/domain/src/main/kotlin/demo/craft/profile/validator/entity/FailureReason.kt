package demo.craft.profile.validator.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class FailureReason(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToOne
    val userProfileValidatorWorkflow: UserProfileValidatorWorkflow,
    val reason: String,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null
)