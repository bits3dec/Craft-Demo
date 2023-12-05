package demo.craft.product.registry.dao.impl.repository

import demo.craft.common.domain.enums.Product
import demo.craft.product.registry.domain.entity.ProductSubscription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductSubscriptionRepository : JpaRepository<ProductSubscription, Long> {
    fun findAllByUserId(userId: String): List<ProductSubscription>

    fun findByUserIdAndProduct(userId: String, product: Product): ProductSubscription?
}