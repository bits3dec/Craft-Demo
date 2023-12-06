package demo.craft.user.profile.workflow.integration

import demo.craft.common.domain.enums.Product

interface ProductRegistryService {
    fun getAllSubscribedProductsForUser(userId: String): List<Product>
}