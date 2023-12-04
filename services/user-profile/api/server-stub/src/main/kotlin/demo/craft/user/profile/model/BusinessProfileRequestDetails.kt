package demo.craft.user.profile.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import demo.craft.user.profile.model.RequestOperation
import demo.craft.user.profile.model.RequestStatus
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * 
 * @param requestId 
 * @param operation 
 * @param status 
 */
data class BusinessProfileRequestDetails(

    @get:NotNull 
    @JsonProperty("requestId") val requestId: kotlin.String,

    @get:NotNull 
    @JsonProperty("operation") val operation: RequestOperation,

    @get:NotNull 
    @JsonProperty("status") val status: RequestStatus
) {

}

