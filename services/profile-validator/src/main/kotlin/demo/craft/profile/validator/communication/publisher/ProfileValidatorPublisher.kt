package demo.craft.profile.validator.communication.publisher

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.communication.kafka.KafkaPublisher
import demo.craft.common.domain.kafka.impl.UserProfileValidationConfirmationMessage
import demo.craft.profile.validator.common.config.ProfileValidatorProperties
import mu.KotlinLogging
import org.springframework.stereotype.Component

/**
 * This class is responsible for sending messages to kafka.
 * This class does not have any core business logic and is only responsible sending messages.
 */
@Component
class ProfileValidatorPublisher(
    private val kafkaPublisher: KafkaPublisher,
    profileValidatorProperties: ProfileValidatorProperties,
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    private val kafkaProperties = profileValidatorProperties.kafka

    fun publishProfileRequestMessage(
        userProfileValidationConfirmationMessage: UserProfileValidationConfirmationMessage
    ) {
        kafkaPublisher.publish(
            topic = kafkaProperties.userProfileValidationConfirmationTopic,
            key = userProfileValidationConfirmationMessage.userId.hashCode(), // Using "userId" as key so that all messages belonging to same user is in order
            payload = objectMapper.writeValueAsString(userProfileValidationConfirmationMessage)
        )
    }
}