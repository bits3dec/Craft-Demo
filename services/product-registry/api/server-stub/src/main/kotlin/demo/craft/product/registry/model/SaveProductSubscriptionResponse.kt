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
 * @param productSubscription 
 */
data class SaveProductSubscriptionResponse(

    @get:NotNull 
    @JsonProperty("productSubscription") val productSubscription: ProductSubscription
) {

}

