package demo.craft.profile.validator.communication.listener

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.communication.kafka.KafkaPublisher
import demo.craft.common.domain.kafka.impl.UserProfileValidationRequestMessage
import demo.craft.profile.validator.common.config.ProfileValidatorProperties
import demo.craft.profile.validator.service.ProfileValidatorService
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * This class is responsible for consuming messages from kafka.
 * This class does not have any core business logic and is only responsible consuming messages.
 */
@Component
class ProfileValidatorListener(
    private val profileValidatorService: ProfileValidatorService,
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
    fun onUserProfileValidationRequestTopicMessage(message: String) {
        val topicName = kafkaProperties.userProfileValidationRequestTopic
        log.info { "Received kafka message. Topic: $topicName. Message: $message" }

        val userProfileValidationRequestMessage =
            objectMapper.readValue(message, UserProfileValidationRequestMessage::class.java)
        log.info { "Received kafka message. Topic: $topicName. Message: $userProfileValidationRequestMessage" }

        profileValidatorService.createUserProfileValidatorWorkflow(
            userId = userProfileValidationRequestMessage.userId,
            requestId = userProfileValidationRequestMessage.requestId,
            operation = userProfileValidationRequestMessage.operation,
            userProfileMessage = userProfileValidationRequestMessage.userProfileMessage,
        )
    }
}