package demo.craft.user.profile.service.controller

import demo.craft.user.profile.api.BusinessProfileApi
import demo.craft.user.profile.model.GetBusinessProfileResponse
import demo.craft.user.profile.service.service.BusinessProfileService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserProfileController(
    val businessProfileService: BusinessProfileService
) : BusinessProfileApi {
    private val log = KotlinLogging.logger {}

    override fun getBusinessProfile(xMinusUserMinusId: String): ResponseEntity<GetBusinessProfileResponse> {
        log.debug { "Received request in [User-Profile] Controller." }
        TODO()
    }
}