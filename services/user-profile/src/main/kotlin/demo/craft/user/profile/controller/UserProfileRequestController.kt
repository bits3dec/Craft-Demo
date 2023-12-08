package demo.craft.user.profile.controller

import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.user.profile.api.UserProfileRequestApi
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
class UserProfileRequestController(
    val userProfileRequestService: UserProfileRequestService
) : UserProfileRequestApi {
    private val log = KotlinLogging.logger {}

    override fun createUserProfile(
        xMinusUserMinusId: String,
        createBusinessProfileRequest: CreateUserProfileRequest
    ): ResponseEntity<CreateUserProfileResponse> {
        log.debug { "Received request in [User-Profile] Controller to create business profile." }

        val userProfileMessage = createBusinessProfileRequest.userProfile.toUserProfileMessage()
        validate(userProfileMessage)

        return ResponseEntity.ok(
            CreateUserProfileResponse(
                requestUuid = userProfileRequestService.triggerCreateUserProfileRequest(xMinusUserMinusId, userProfileMessage).requestId
            )
        )
    }

    override fun getUserProfileRequestDetails(
        xMinusUserMinusId: String,
        requestId: String
    ): ResponseEntity<GetUserProfileRequestDetailsResponse> {
        log.debug { "Received request in [User-Profile] Controller to fetch business profile." }
        return ResponseEntity.ok(
            GetUserProfileRequestDetailsResponse(
                requestDetails = userProfileRequestService.getUserProfileRequestDetails(xMinusUserMinusId, requestId).toApiModel()
            )
        )
    }

    override fun updateUserProfile(
        xMinusUserMinusId: String,
        updateBusinessProfileRequest: UpdateUserProfileRequest
    ): ResponseEntity<UpdateUserProfileResponse> {
        log.debug { "Received request in [User-Profile] Controller to update business profile." }

        val userProfileMessage = updateBusinessProfileRequest.userProfile.toUserProfileMessage()
        validate(userProfileMessage)

        return ResponseEntity.ok(
            UpdateUserProfileResponse(
                requestUuid = userProfileRequestService.triggerUpdateUserProfileRequest(xMinusUserMinusId, userProfileMessage).requestId
            )
        )
    }

    private fun validate(userProfileMessage: UserProfileMessage) {
        val invalidFields = userProfileMessage.validateFields()
        if (invalidFields.isNotEmpty()) {
            throw InvalidUserProfileRequestException(invalidFields.toString())
        }
    }
}