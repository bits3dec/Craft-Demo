package demo.craft.user.profile.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import demo.craft.user.profile.model.BusinessProfile
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * 
 * @param businessProfile 
 */
data class GetBusinessProfileResponse(

    @get:NotNull 
    @JsonProperty("businessProfile") val businessProfile: BusinessProfile
) {

}

