package demo.craft.user.profile.service.service

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.communication.kafka.KafkaPublisher
import demo.craft.user.profile.common.config.UserProfileProperties
import demo.craft.user.profile.service.lock.UserProfileLockManager
import mu.KotlinLogging

class BusinessProfileService(
    private val kafkaPublisher: KafkaPublisher,
    private val lockManager: UserProfileLockManager,
    userProfileProperties: UserProfileProperties
) {
    private val log = KotlinLogging.logger {}
    private val kafkaProperties = userProfileProperties.kafka
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }

    fun testKafkaPublisher(userId: String) {
        val message = "testing message publish"
        try {
            kafkaPublisher.publish(
                kafkaProperties.userProfileRequestTopic,
                userId.hashCode(),
                objectMapper.writeValueAsString(message))
        } catch (e: Exception) {
            log.error { "Failed to publish change request in kafka. payload: $message" }
        }
    }

    fun testKafkaListener() {

    }
}