package demo.craft.user.profile.communication.publisher

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.communication.kafka.KafkaPublisher
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.common.domain.kafka.impl.UserProfileRequestMessage
import demo.craft.user.profile.common.config.UserProfileProperties
import demo.craft.user.profile.domain.entity.UserProfileRequest
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.Instant

/**
 * This class is responsible for sending messages to kafka.
 * This class does not have any core business logic and is only responsible sending messages.
 */
@Component
class UserProfilePublisher(
    private val kafkaPublisher: KafkaPublisher,
    userProfileProperties: UserProfileProperties,
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    private val kafkaProperties = userProfileProperties.kafka

    fun publishProfileRequestMessage(userProfileRequest: UserProfileRequest) {
        val userProfileRequestMessage =
            UserProfileRequestMessage(
                userId = userProfileRequest.userId,
                requestId = userProfileRequest.requestId,
                operation = userProfileRequest.operation,
                timestamp = Timestamp.from(Instant.now()),
                state = userProfileRequest.state,
                userProfileMessage = objectMapper.readValue(userProfileRequest.newValue, UserProfileMessage::class.java)
            )
        kafkaPublisher.publish(
            topic = kafkaProperties.userProfileRequestTopic,
            key = userProfileRequestMessage.userId.hashCode(), // Using "userId" as key so that all messages belonging to same user is in order
            payload = objectMapper.writeValueAsString(userProfileRequestMessage)
        )
    }
}