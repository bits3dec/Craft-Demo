package demo.craft.user.profile.workflow.entity

import demo.craft.common.domain.enums.Operation
import demo.craft.user.profile.workflow.entity.enums.WorkflowState
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class UserProfileWorkflow(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: String,
    val requestId: String,
    val operation: Operation,

    // Json string value of the "user profile"
    val newValue: String,
    val state: WorkflowState,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
)