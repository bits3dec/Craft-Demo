package demo.craft.user.profile.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

// TODO: Implement HashCode and Equals to do deep comparison which is needed incase of updates
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
) {
    /**
     * Implemented equals() and hashCode() to do deep comparison.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Address

        return when {
            line1 != other.line1 -> false
            line2 != other.line2 -> false
            city != other.city -> false
            state != other.state -> false
            zip != other.zip -> false
            country != other.country -> false
            else -> true
        }
    }

    override fun hashCode(): Int {
        var result = line1.hashCode()
        result = 31 * result + line2.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + zip.hashCode()
        result = 31 * result + country.hashCode()
        return result
    }
}