package demo.craft.profile.validator.listener

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.communication.kafka.KafkaPublisher
import demo.craft.profile.validator.common.config.ProfileValidatorProperties
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProfileValidatorListener(
    private val kafkaPublisher: KafkaPublisher,
    profileValidatorProperties: ProfileValidatorProperties,
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }

    private val kafkaProperties = profileValidatorProperties.kafka

    @KafkaListener(
        topics = ["\${demo.craft.profile.validator.kafka.userProfileValidationRequestTopic}"]
    )
    fun onUserProfileRequestTopicMessage(message: String) {
        val topicName = kafkaProperties.userProfileValidationRequestTopic
        log.info { "Received kafka message. Topic: $topicName. Message: $message" }
    }
}