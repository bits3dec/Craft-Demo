package demo.craft.user.profile.controller

import demo.craft.user.profile.api.BusinessProfileApi
import demo.craft.user.profile.model.*
import demo.craft.user.profile.mapper.toApiModel
import demo.craft.user.profile.service.UserProfileService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserProfileController(
    val userProfileRequestService: UserProfileService
) : BusinessProfileApi {
    private val log = KotlinLogging.logger {}

    override fun getBusinessProfile(
        xMinusUserMinusId: String,
    ): ResponseEntity<GetBusinessProfileResponse> {
        log.debug { "Received request in [User-Profile] Controller to create business profile." }
        return ResponseEntity.ok(
            GetBusinessProfileResponse(
                userProfile = userProfileRequestService.getUserProfile(xMinusUserMinusId).toApiModel()
            )
        )
    }
}