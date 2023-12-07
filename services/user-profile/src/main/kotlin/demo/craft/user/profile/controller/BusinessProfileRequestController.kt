package demo.craft.user.profile.controller

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.user.profile.api.BusinessProfileRequestApi
import demo.craft.user.profile.common.exception.InvalidUserProfileRequestException
import demo.craft.user.profile.controller.validations.validateFields
import demo.craft.user.profile.model.*
import demo.craft.user.profile.mapper.toApiModel
import demo.craft.user.profile.mapper.toUserProfileMessage
import demo.craft.user.profile.service.UserProfileRequestService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BusinessProfileRequestController(
    val userProfileRequestService: UserProfileRequestService
) : BusinessProfileRequestApi {
    private val log = KotlinLogging.logger {}

    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    override fun createBusinessProfile(
        xMinusUserMinusId: String,
        createBusinessProfileRequest: CreateBusinessProfileRequest
    ): ResponseEntity<CreateBusinessProfileResponse> {
        log.debug { "Received request in [User-Profile] Controller to create business profile." }

        val userProfileMessage = createBusinessProfileRequest.businessProfile.toUserProfileMessage()
        validate(xMinusUserMinusId, userProfileMessage)

        return ResponseEntity.ok(
            CreateBusinessProfileResponse(
                requestUuid = userProfileRequestService.triggerCreateUserProfileRequest(xMinusUserMinusId, userProfileMessage).requestId
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

        val userProfileMessage = updateBusinessProfileRequest.businessProfile.toUserProfileMessage()
        validate(xMinusUserMinusId, userProfileMessage)

        return ResponseEntity.ok(
            UpdateBusinessProfileResponse(
                requestUuid = userProfileRequestService.triggerUpdateUserProfileRequest(xMinusUserMinusId, userProfileMessage).requestId
            )
        )
    }

    private fun validate(userId: String, userProfileMessage: UserProfileMessage) {
        val invalidFields = userProfileMessage.validateFields()
        if (invalidFields.isNotEmpty()) {
            throw InvalidUserProfileRequestException(invalidFields.toString())
        }
    }
}