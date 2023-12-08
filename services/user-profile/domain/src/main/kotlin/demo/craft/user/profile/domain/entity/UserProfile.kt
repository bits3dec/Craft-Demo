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
    val website: String,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
) {

    /**
     * Implemented equals() and hashCode() to do deep comparison.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserProfile

        return when {
            userId != other.userId -> false
            companyName != other.companyName -> false
            legalName != other.legalName -> false
            businessAddress != other.businessAddress -> false
            legalAddress != other.legalAddress -> false
            taxIdentifier != other.taxIdentifier -> false
            email != other.email -> false
            website != other.website -> false
            else -> true
        }
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + companyName.hashCode()
        result = 31 * result + legalName.hashCode()
        result = 31 * result + businessAddress.hashCode()
        result = 31 * result + legalAddress.hashCode()
        result = 31 * result + taxIdentifier.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + website.hashCode()
        return result
    }
}
