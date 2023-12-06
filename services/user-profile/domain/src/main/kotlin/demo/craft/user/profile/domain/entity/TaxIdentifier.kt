package demo.craft.user.profile.domain.entity

import demo.craft.common.domain.enums.TaxType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class TaxIdentifier(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val value: String,
    @Enumerated(EnumType.STRING)
    val type: TaxType,
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

        other as TaxIdentifier

        return when {
            value != other.value -> false
            type != other.type -> false
            else -> true
        }
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}