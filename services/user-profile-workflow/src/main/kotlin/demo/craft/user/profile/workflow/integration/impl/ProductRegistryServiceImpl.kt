package demo.craft.user.profile.workflow.integration.impl

import demo.craft.common.domain.enums.Product
import demo.craft.product.registry.client.api.ProductRegistryApi
import demo.craft.user.profile.workflow.integration.ProductRegistryService
import org.springframework.stereotype.Component

@Component
class ProductRegistryServiceImpl(
    private val productRegistryApi: ProductRegistryApi
) : ProductRegistryService {
    override fun getAllSubscribedProductsForUser(userId: String): List<Product> {
        return productRegistryApi.getAllProductSubscriptions(userId)
            .body?.productSubscriptions!!.map { Product.valueOf(it.product.value) }
    }
}
