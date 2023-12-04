package demo.craft.product.registry.controller

import demo.craft.product.registry.api.ProductRegistryApi
import demo.craft.product.registry.model.*
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductRegistryController : ProductRegistryApi {
    private val log = KotlinLogging.logger {}

    override fun getAllProductSubscriptions(xMinusUserMinusId: String): ResponseEntity<GetAllProductSubscriptionsResponse> {
        log.debug { "Request received in [Product-Registry] Controller." }
        TODO()
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