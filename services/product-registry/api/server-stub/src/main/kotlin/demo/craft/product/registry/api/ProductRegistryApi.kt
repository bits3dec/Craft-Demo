/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
*/
package demo.craft.product.registry.api

import demo.craft.product.registry.model.GetAllProductSubscriptionsResponse
import demo.craft.product.registry.model.SaveProductSubscriptionRequest
import demo.craft.product.registry.model.SaveProductSubscriptionResponse
import demo.craft.product.registry.model.UpdateProductSubscriptionRequest
import demo.craft.product.registry.model.UpdateProductSubscriptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.validation.annotation.Validated
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.beans.factory.annotation.Autowired

import javax.validation.Valid
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

import kotlin.collections.List
import kotlin.collections.Map

@Validated
@RequestMapping("\${api.base-path:}")
interface ProductRegistryApi {


    @RequestMapping(
            value = ["/product-registry/v1/user/products"],
            produces = ["application/json"], 
            method = [RequestMethod.GET])
    fun getAllProductSubscriptions( @RequestHeader(value="x-user-id", required=true) xMinusUserMinusId: kotlin.String
): ResponseEntity<GetAllProductSubscriptionsResponse> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
            value = ["/product-registry/v1/user/products"],
            produces = ["application/json"], 
            consumes = ["application/json"],
            method = [RequestMethod.POST])
    fun saveProductSubscription( @RequestHeader(value="x-user-id", required=true) xMinusUserMinusId: kotlin.String
, @Valid @RequestBody saveProductSubscriptionRequest: SaveProductSubscriptionRequest
): ResponseEntity<SaveProductSubscriptionResponse> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
            value = ["/product-registry/v1/user/products"],
            produces = ["application/json"], 
            consumes = ["application/json"],
            method = [RequestMethod.PUT])
    fun updateProductSubscription( @RequestHeader(value="x-user-id", required=true) xMinusUserMinusId: kotlin.String
, @Valid @RequestBody updateProductSubscriptionRequest: UpdateProductSubscriptionRequest
): ResponseEntity<UpdateProductSubscriptionResponse> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
