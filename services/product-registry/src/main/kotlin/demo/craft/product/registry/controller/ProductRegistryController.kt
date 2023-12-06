package demo.craft.product.registry.controller

import demo.craft.product.registry.api.ProductRegistryApi
import demo.craft.product.registry.model.*
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController


@RestController
class ProductRegistryController : ProductRegistryApi {
    private val log = KotlinLogging.logger {}

    /*
     TODO:
         1. Hardcoding product subscription for now. Implement controller -> service -> dao.
         2. Add exception handling.
    */
    override fun getAllProductSubscriptions(xMinusUserMinusId: String): ResponseEntity<GetAllProductSubscriptionsResponse> {
        log.debug { "Request received in [Product-Registry] Controller." }
        val products = listOf(
            Product.QUICKBOOKS_ACCOUNTING,
            Product.QUICKBOOKS_PAYROLL,
            Product.QUICKBOOKS_PAYMENTS,
            Product.TSHEETS
        )

        val productSubscriptions = mutableListOf<ProductSubscription>()
        products.forEach { product ->
            productSubscriptions.add(
                ProductSubscription(
                    product = product,
                    status = ProductSubscriptionStatus.ACTIVE
                )
            )
        }
        return ResponseEntity.ok(
            GetAllProductSubscriptionsResponse(productSubscriptions = productSubscriptions)
        )
    }

    override fun saveProductSubscription(
        xMinusUserMinusId: String,
        saveProductSubscriptionRequest: SaveProductSubscriptionRequest
    ): ResponseEntity<SaveProductSubscriptionResponse> {
        log.debug { "Request received in [Product-Registry] Controller." }
        TODO()
    }

    override fun updateProductSubscription(
        xMinusUserMinusId: String,
        updateProductSubscriptionRequest: UpdateProductSubscriptionRequest
    ): ResponseEntity<UpdateProductSubscriptionResponse> {
        log.debug { "Request received in [Product-Registry] Controller." }
        TODO()
    }
}