package demo.craft.user.profile.communication.listener

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.domain.kafka.impl.UserProfileRequestConfirmationMessage
import demo.craft.user.profile.common.config.UserProfileProperties
import demo.craft.user.profile.service.UserProfileRequestService
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * This class is responsible for consuming messages from kafka.
 * This class does not have any core business logic and is only responsible consuming messages.
 */
@Component
class UserProfileListener(
    private val userProfileRequestService: UserProfileRequestService,
    private val userProfileProperties: UserProfileProperties
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    private val kafkaProperties = userProfileProperties.kafka

    @KafkaListener(
        topics = ["\${demo.craft.user.profile.kafka.userProfileRequestConfirmationTopic}"]
    )
    fun userProfileRequestConfirmationTopicMessage(message: String) {
        val topicName = kafkaProperties.userProfileRequestConfirmationTopic
        log.info { "Received kafka message. Topic: $topicName. Message: $message" }

        val userProfileRequestConfirmation =
            objectMapper.readValue(message, UserProfileRequestConfirmationMessage::class.java)
        log.debug { "Post deserialization. Topic: $topicName. Message: $userProfileRequestConfirmation" }

        userProfileRequestService.commitUserProfileRequest(
            userId = userProfileRequestConfirmation.userId,
            requestId = userProfileRequestConfirmation.requestId,
            state = userProfileRequestConfirmation.state
        )
    }
}