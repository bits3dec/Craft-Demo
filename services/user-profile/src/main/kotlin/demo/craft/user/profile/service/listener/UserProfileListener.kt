package demo.craft.user.profile.service.listener

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.communication.kafka.KafkaPublisher
import demo.craft.user.profile.common.config.UserProfileProperties
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class UserProfileListener(
    private val kafkaPublisher: KafkaPublisher,
    userProfileProperties: UserProfileProperties,
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }

    private val kafkaProperties = userProfileProperties.kafka

    @KafkaListener(
        topics = ["\${demo.craft.user.profile.kafka.userProfileRequestConfirmationTopic}"]
    )
    fun onMessage(message: String) {
        val topicName = kafkaProperties.userProfileRequestConfirmationTopic
        log.info { "Received kafka message. Topic: $topicName. Message: $message" }

        val message = objectMapper.readValue(message, String::class.java)
        log.info { "Post deserialization. Message: $message" }
    }
}