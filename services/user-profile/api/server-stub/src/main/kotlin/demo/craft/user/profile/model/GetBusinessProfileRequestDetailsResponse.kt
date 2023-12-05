package demo.craft.user.profile.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import demo.craft.user.profile.model.BusinessProfileRequestDetails
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * 
 * @param requestDetails 
 */
data class GetBusinessProfileRequestDetailsResponse(

    @get:NotNull 
    @JsonProperty("requestDetails") val requestDetails: BusinessProfileRequestDetails
) {

}

