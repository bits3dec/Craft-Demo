package demo.craft.user.profile.communication.listener

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.user.profile.common.config.UserProfileProperties
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * This class is responsible for consuming messages from kafka.
 * This class does not have any core business logic and is only responsible consuming messages.
 */
@Component
class UserProfileListener(
    private val userProfileProperties: UserProfileProperties
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
    fun userProfileRequestConfirmationTopicMessage(message: String) {
        val topicName = kafkaProperties.userProfileRequestConfirmationTopic
        log.info { "Received kafka message. Topic: $topicName. Message: $message" }

        /*
         TODO: Handle the profile request confirmation in "User Profile Service".
             1. ACCEPTED:
                a) Update the entry in "user-profile-request" table with ACCEPTED.
                b) Create or Update entry in original "user-profile" table.
                c) Add entry in "user-profile-history" table.
             2. REJECTED:
                a) Update the entry in "user-profile-request" table with FAILURE.
         */

    }
}