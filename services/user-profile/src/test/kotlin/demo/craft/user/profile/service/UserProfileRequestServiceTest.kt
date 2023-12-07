package demo.craft.user.profile.service

import demo.craft.common.domain.enums.TaxType
import demo.craft.common.domain.kafka.impl.TaxIdentifierMessage
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.user.constants.USER_1
import demo.craft.user.constants.USER_PROFILE
import demo.craft.user.constants.USER_PROFILE_MESSAGE
import demo.craft.user.profile.common.cache.GenericCacheManager
import demo.craft.user.profile.common.lock.UserProfileLockManager
import demo.craft.user.profile.communication.publisher.UserProfilePublisher
import demo.craft.user.profile.dao.UserProfileAccess
import demo.craft.user.profile.mapper.toApiModel
import demo.craft.user.profile.model.UserProfile
import demo.craft.user.profile.toKafkaMessageModel
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class UserProfileRequestServiceTest {

    @MockK
    private lateinit var userProfileAccess: UserProfileAccess

    @MockK
    private lateinit var userProfilePublisher: UserProfilePublisher

    @MockK
    private lateinit var userProfileLockManager: UserProfileLockManager

    @MockK
    private lateinit var cacheManager: GenericCacheManager

    @InjectMockKs
    private lateinit var businessProfileService: UserProfileRequestService


    @Test
    fun triggerCreateUserProfileRequest() {
        // given

        // when
        businessProfileService.triggerCreateUserProfileRequest(USER_1, USER_PROFILE_MESSAGE)

        // then

    }

    @Test
    fun getUserProfileRequestDetails() {
    }

    @Test
    fun triggerUpdateUserProfileRequest() {
    }

    @Test
    fun commitUserProfileRequest() {

    }

    fun UserProfile.toUserProfileMessage() =
        UserProfileMessage(
            companyName = this.companyName,
            legalName = this.legalName,
            businessAddress = this.businessAddress.toKafkaMessageModel(),
            legalAddress = this.legalAddress.toKafkaMessageModel(),
            taxIdentifier =
            if (this.pan != null) {
                TaxIdentifierMessage(value = this.pan!!, type = TaxType.PAN)
            } else {
                TaxIdentifierMessage(value = this.ein!!, type = TaxType.EIN)
            },
            email = this.email,
            website = this.website
        )
}