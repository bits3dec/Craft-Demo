package demo.craft.product.registry.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import demo.craft.product.registry.model.Product
import demo.craft.product.registry.model.ProductSubscriptionStatus
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * 
 * @param product 
 * @param status 
 */
data class ProductSubscription(

    @get:NotNull 
    @JsonProperty("product") val product: Product,

    @get:NotNull 
    @JsonProperty("status") val status: ProductSubscriptionStatus
) {

}

