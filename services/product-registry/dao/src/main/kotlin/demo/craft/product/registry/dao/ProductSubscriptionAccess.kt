package demo.craft.product.registry.dao

import demo.craft.common.domain.enums.Product
import demo.craft.product.registry.domain.entity.ProductSubscription

interface ProductSubscriptionAccess {
    fun findByUserId(userId: String): List<ProductSubscription>

    fun findByUserIdAndProduct(userId: String, product: Product): ProductSubscription?
}