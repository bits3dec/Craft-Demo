package demo.craft.user.profile.controller

import demo.craft.user.profile.api.BusinessProfileRequestApi
import demo.craft.user.profile.model.*
import demo.craft.user.profile.mapper.toApiModel
import demo.craft.user.profile.service.UserProfileRequestService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BusinessProfileRequestController(
    val userProfileRequestService: UserProfileRequestService
) : BusinessProfileRequestApi {
    private val log = KotlinLogging.logger {}

    override fun createBusinessProfile(
        xMinusUserMinusId: String,
        createBusinessProfileRequest: CreateBusinessProfileRequest
    ): ResponseEntity<CreateBusinessProfileResponse> {
        log.debug { "Received request in [User-Profile] Controller to create business profile." }
        return ResponseEntity.ok(
            CreateBusinessProfileResponse(
                requestUuid = userProfileRequestService.triggerCreateUserProfileRequest(xMinusUserMinusId, createBusinessProfileRequest).requestId
            )
        )
    }

    override fun getBusinessProfileRequestDetails(
        xMinusUserMinusId: String,
        requestId: String
    ): ResponseEntity<GetBusinessProfileRequestDetailsResponse> {
        log.debug { "Received request in [User-Profile] Controller to fetch business profile." }
        return ResponseEntity.ok(
            GetBusinessProfileRequestDetailsResponse(
                requestDetails = userProfileRequestService.getUserProfileRequestDetails(xMinusUserMinusId, requestId).toApiModel()
            )
        )
    }

    override fun updateBusinessProfile(
        xMinusUserMinusId: String,
        updateBusinessProfileRequest: UpdateBusinessProfileRequest
    ): ResponseEntity<UpdateBusinessProfileResponse> {
        log.debug { "Received request in [User-Profile] Controller to update business profile." }
        return ResponseEntity.ok(
            UpdateBusinessProfileResponse(
                requestUuid = userProfileRequestService.triggerUpdateUserProfileRequest(xMinusUserMinusId, updateBusinessProfileRequest).requestId
            )
        )
    }
}