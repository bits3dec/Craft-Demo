package demo.craft.user.profile.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * 
 * @param requestUuid 
 */
data class UpdateBusinessProfileResponse(

    @get:NotNull 
    @JsonProperty("requestUuid") val requestUuid: kotlin.String
) {

}

