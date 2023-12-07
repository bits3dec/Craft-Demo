package demo.craft.user.profile.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.user.profile.common.cache.GenericCacheManager
import demo.craft.user.profile.common.config.UserProfileProperties
import demo.craft.user.profile.common.exception.UserProfileNotFoundException
import demo.craft.user.profile.dao.UserProfileAccess
import demo.craft.user.profile.domain.entity.UserProfile
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class UserProfileService(
    private val userProfileAccess: UserProfileAccess,
    private val cacheManager: GenericCacheManager
) {
    private val log = KotlinLogging.logger {}

    fun getUserProfile(userId: String): UserProfile {
        log.debug { "Received request in [User-Profile] Controller to fetch business profile." }

        return cacheManager.lookup(
            userId,
            object : TypeReference<UserProfile>(){}
        ) {
            userProfileAccess.findUserProfileByUserId(userId)
        }
            ?: throw UserProfileNotFoundException()
    }
}
