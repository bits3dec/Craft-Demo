package demo.craft.user.profile.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import demo.craft.user.profile.model.Address
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * 
 * @param companyName 
 * @param legalName 
 * @param businessAddress 
 * @param legalAddress 
 * @param email 
 * @param website 
 * @param pan 
 * @param ein 
 */
data class BusinessProfile(

    @get:NotNull 
    @JsonProperty("companyName") val companyName: kotlin.String,

    @get:NotNull 
    @JsonProperty("legalName") val legalName: kotlin.String,

    @get:NotNull 
    @JsonProperty("businessAddress") val businessAddress: Address,

    @get:NotNull 
    @JsonProperty("legalAddress") val legalAddress: Address,

    @get:NotNull 
    @JsonProperty("email") val email: kotlin.String,

    @get:NotNull 
    @JsonProperty("website") val website: kotlin.String,

    @JsonProperty("pan") val pan: kotlin.String? = null,

    @JsonProperty("ein") val ein: kotlin.String? = null
) {

}

