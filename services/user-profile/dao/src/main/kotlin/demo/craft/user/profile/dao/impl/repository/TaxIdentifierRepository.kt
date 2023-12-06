package demo.craft.user.profile.dao.impl.repository

import demo.craft.user.profile.domain.entity.TaxIdentifier
import org.springframework.data.jpa.repository.JpaRepository

interface TaxIdentifierRepository : JpaRepository<TaxIdentifier, Long>