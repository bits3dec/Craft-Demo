package demo.craft.user.profile.service

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
    private val genericCacheManager: GenericCacheManager,
    private val userProfileProperties: UserProfileProperties
) {
    private val log = KotlinLogging.logger {}
    private val kafkaProperties = userProfileProperties.kafka
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
    }

    // TODO: Use cache
    fun getUserProfile(userId: String): UserProfile {
        log.debug { "Received request in [User-Profile] Controller to fetch business profile." }
        return userProfileAccess.findUserProfileByUserId(userId)
            ?: throw UserProfileNotFoundException(userId)
    }
}
