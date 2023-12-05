package demo.craft.user.profile.workflow.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class UserProfileWorkflowHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    val userProfileWorkflow: UserProfileWorkflow,

    // Json string value of the "user profile workflow"
    val value: String,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
)