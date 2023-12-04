package demo.craft.product.registry.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import demo.craft.product.registry.model.ProductSubscription
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * 
 * @param productSubscriptions 
 */
data class GetAllProductSubscriptionsResponse(

    @get:NotNull 
    @JsonProperty("productSubscriptions") val productSubscriptions: kotlin.collections.List<ProductSubscription>
) {

}

