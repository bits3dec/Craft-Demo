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
 * @param line1 
 * @param line2 
 * @param city 
 * @param state 
 * @param country 
 * @param zip 
 */
data class Address(

    @get:NotNull 
    @JsonProperty("line1") val line1: kotlin.String,

    @get:NotNull 
    @JsonProperty("line2") val line2: kotlin.String,

    @get:NotNull 
    @JsonProperty("city") val city: kotlin.String,

    @get:NotNull 
    @JsonProperty("state") val state: kotlin.String,

    @get:NotNull 
    @JsonProperty("country") val country: kotlin.String,

    @get:NotNull 
    @JsonProperty("zip") val zip: kotlin.String
) {

}

