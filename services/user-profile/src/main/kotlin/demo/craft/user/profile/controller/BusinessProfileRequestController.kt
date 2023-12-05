package demo.craft.user.profile.controller

import demo.craft.user.profile.api.BusinessProfileRequestApi
import demo.craft.user.profile.model.*
import demo.craft.user.profile.mapper.toApiModel
import demo.craft.user.profile.service.BusinessProfileService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BusinessProfileRequestController(
    val businessProfileService: BusinessProfileService
) : BusinessProfileRequestApi {
    private val log = KotlinLogging.logger {}

    override fun createBusinessProfile(
        xMinusUserMinusId: String,
        createBusinessProfileRequest: CreateBusinessProfileRequest
    ): ResponseEntity<CreateBusinessProfileResponse> {
        log.debug { "Received request in [User-Profile] Controller to create business profile." }
        return ResponseEntity.ok(
            CreateBusinessProfileResponse(
                requestUuid = businessProfileService.createBusinessProfileRequest(xMinusUserMinusId, createBusinessProfileRequest).requestId
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
                requestDetails = businessProfileService.getBusinessProfileRequestDetails(xMinusUserMinusId, requestId).toApiModel()
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
                requestUuid = businessProfileService.updateBusinessProfileRequest(xMinusUserMinusId, updateBusinessProfileRequest).requestId
            )
        )
    }
}