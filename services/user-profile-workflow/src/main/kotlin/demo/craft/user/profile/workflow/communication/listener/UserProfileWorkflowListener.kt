package demo.craft.user.profile.workflow.communication.listener

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.communication.kafka.KafkaPublisher
import demo.craft.common.domain.kafka.impl.UserProfileRequestMessage
import demo.craft.common.domain.kafka.impl.UserProfileValidationConfirmationMessage
import demo.craft.user.profile.workflow.common.config.UserProfileWorkflowProperties
import demo.craft.user.profile.workflow.service.UserProfileWorkflowService
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * This class is responsible for consuming messages from kafka.
 * This class does not have any core business logic and is only responsible consuming messages.
 */
@Component
class UserProfileWorkflowListener(
    private val userProfileWorkflowService: UserProfileWorkflowService,
    userProfileWorkflowProperties: UserProfileWorkflowProperties,
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    private val kafkaProperties = userProfileWorkflowProperties.kafka

    @KafkaListener(
        topics = ["\${demo.craft.user.profile.workflow.kafka.userProfileRequestTopic}"]
    )
    fun onUserProfileRequestTopicMessage(message: String) {
        val topicName = kafkaProperties.userProfileRequestTopic
        log.info { "Received kafka message. Topic: $topicName. Message: $message" }

        val userProfileRequestMessage = objectMapper.readValue(message, UserProfileRequestMessage::class.java)
        log.info { "Received kafka message. Topic: $topicName. Message: $userProfileRequestMessage" }

        userProfileWorkflowService.createUserProfileWork(
            userId = userProfileRequestMessage.userId,
            requestId = userProfileRequestMessage.requestId,
            operation = userProfileRequestMessage.operation,
            userProfileMessage = userProfileRequestMessage.userProfileMessage
        )
    }

    @KafkaListener(
        topics = ["\${demo.craft.user.profile.workflow.kafka.userProfileValidationConfirmationTopic}"]
    )
    fun onUserProfileValidationConfirmationTopicMessage(message: String) {
        val topicName = kafkaProperties.userProfileValidationConfirmationTopic
        log.info { "Received kafka message. Topic: $topicName. Message: $message" }

        val userProfileValidationConfirmationMessage = objectMapper.readValue(message, UserProfileValidationConfirmationMessage::class.java)
        log.info { "Received kafka message. Topic: $topicName. Message: $userProfileValidationConfirmationMessage" }

        userProfileWorkflowService.updateUserProfileWork(
            userId = userProfileValidationConfirmationMessage.userId,
            requestId = userProfileValidationConfirmationMessage.requestId,
            state = userProfileValidationConfirmationMessage.state,
            failureReason = userProfileValidationConfirmationMessage.failureReason,
        )
    }
}