package demo.craft.user.profile.workflow.integration.product.registry

import demo.craft.common.domain.enums.Product

interface ProductRegistryService {
    fun getAllSubscribedProductsForUser(userId: String): List<Product>
}