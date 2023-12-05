package demo.craft.product.registry.dao.impl

import demo.craft.common.domain.enums.Product
import demo.craft.product.registry.dao.ProductSubscriptionAccess
import demo.craft.product.registry.domain.entity.ProductSubscription
import org.springframework.stereotype.Component

@Component
class ProductSubscriptionAccessDbImpl : ProductSubscriptionAccess {
    override fun findByUserId(userId: String): List<ProductSubscription> {
        TODO("Not yet implemented")
    }

    override fun findByUserIdAndProduct(userId: String, product: Product): ProductSubscription? {
        TODO("Not yet implemented")
    }
}