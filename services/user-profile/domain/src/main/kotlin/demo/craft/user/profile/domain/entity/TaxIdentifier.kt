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
)