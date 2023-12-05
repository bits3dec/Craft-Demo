package demo.craft.user.profile.workflow.listener

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.communication.kafka.KafkaPublisher
import demo.craft.user.profile.workflow.common.config.UserProfileWorkflowProperties
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class UserProfileWorkflowListener(
    private val kafkaPublisher: KafkaPublisher,
    userProfileWorkflowProperties: UserProfileWorkflowProperties,
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }

    private val kafkaProperties = userProfileWorkflowProperties.kafka

    @KafkaListener(
        topics = ["\${demo.craft.user.profile.workflow.kafka.userProfileRequestTopic}"]
    )
    fun onUserProfileRequestTopicMessage(message: String) {
        val topicName = kafkaProperties.userProfileRequestTopic
        log.info { "Received kafka message. Topic: $topicName. Message: $message" }
    }

    @KafkaListener(
        topics = ["\${demo.craft.user.profile.workflow.kafka.userProfileValidationConfirmationTopic}"]
    )
    fun onUserProfileValidationConfirmationTopicMessage(message: String) {
        val topicName = kafkaProperties.userProfileValidationConfirmationTopic
        log.info { "Received kafka message. Topic: $topicName. Message: $message" }
    }
}