package demo.craft.user.profile.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class UserProfile(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: String,
    val companyName: String,
    val legalName: String,
    @OneToOne
    val businessAddress: Address,
    @OneToOne
    val legalAddress: Address,
    @OneToOne
    val taxIdentifier: TaxIdentifier,
    val email: String,
    val website: String? = null,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
)