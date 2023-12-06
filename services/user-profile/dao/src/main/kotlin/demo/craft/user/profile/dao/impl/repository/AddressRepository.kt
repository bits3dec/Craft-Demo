package demo.craft.user.profile.dao.impl.repository

import demo.craft.user.profile.domain.entity.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Long>