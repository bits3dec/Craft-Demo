package demo.craft.user.profile.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val line1: String,
    val line2: String,
    val city: String,
    val state: String,
    val country: String,
    val zip: String,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
)