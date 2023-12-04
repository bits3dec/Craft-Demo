package demo.craft.user.profile.service.service

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.communication.kafka.KafkaPublisher
import demo.craft.user.profile.common.config.UserProfileProperties
import demo.craft.user.profile.common.cache.GenericCacheManager
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class BusinessProfileService(
    private val kafkaPublisher: KafkaPublisher,
    private val genericCacheManager: GenericCacheManager,
    private val userProfileProperties: UserProfileProperties
) {
    private val log = KotlinLogging.logger {}
    private val kafkaProperties = userProfileProperties.kafka
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }
}
