package demo.craft.user.profile.workflow.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class UserProfileWorkflowFailureReason(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToOne
    val userProfileWorkflow: UserProfileWorkflow,
    val reason: String,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null
)