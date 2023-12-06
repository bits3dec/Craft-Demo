package demo.craft.user.profile.workflow.communication.publisher

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.communication.kafka.KafkaPublisher
import demo.craft.common.domain.enums.Product
import demo.craft.common.domain.enums.State
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.common.domain.kafka.impl.UserProfileRequestConfirmationMessage
import demo.craft.common.domain.kafka.impl.UserProfileValidationRequestMessage
import demo.craft.user.profile.workflow.common.config.UserProfileWorkflowProperties
import demo.craft.user.profile.workflow.entity.UserProfileWorkflow
import demo.craft.user.profile.workflow.entity.UserProfileWorkflowFailureReason
import demo.craft.user.profile.workflow.entity.enums.WorkflowState
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.Instant
import javax.transaction.NotSupportedException

/**
 * This class is responsible for sending messages to kafka.
 * This class does not have any core business logic and is only responsible sending messages.
 */
@Component
class UserProfileWorkflowPublisher(
    private val kafkaPublisher: KafkaPublisher,
    userProfileWorkflowProperties: UserProfileWorkflowProperties,
) {
    private val log = KotlinLogging.logger {}
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    private val kafkaProperties = userProfileWorkflowProperties.kafka

    fun publishProfileValidationRequestMessage(userProfileWorkflow: UserProfileWorkflow, products: List<Product>) {
        val userProfileRequestConfirmationMessage =
            UserProfileValidationRequestMessage(
                userId = userProfileWorkflow.userId,
                requestId = userProfileWorkflow.requestId,
                operation = userProfileWorkflow.operation,
                userProfileMessage = objectMapper.readValue(userProfileWorkflow.newValue, UserProfileMessage::class.java),
                timestamp = Timestamp.from(Instant.now()),
                products = products
            )
        kafkaPublisher.publish(
            topic = kafkaProperties.userProfileValidationRequestTopic,
            key = userProfileWorkflow.userId.hashCode(), // Using "userId" as key so that all messages belonging to same user is in order
            payload = objectMapper.writeValueAsString(userProfileRequestConfirmationMessage)
        )
    }

    fun publishProfileRequestConfirmationMessage(userProfileWorkflow: UserProfileWorkflow, userProfileWorkflowFailureReason: UserProfileWorkflowFailureReason?) {
        val state =
            when (userProfileWorkflow.state) {
                WorkflowState.ACCEPTED -> State.ACCEPTED
                WorkflowState.FAILURE -> State.REJECTED
                else -> {
                    throw NotSupportedException("Not a valid state: $userProfileWorkflow.state")
                }
            }

        val userProfileRequestConfirmationMessage =
            UserProfileRequestConfirmationMessage(
                userId = userProfileWorkflow.userId,
                requestId = userProfileWorkflow.requestId,
                timestamp = Timestamp.from(Instant.now()),
                failureReason = userProfileWorkflowFailureReason?.reason,
                operation = userProfileWorkflow.operation,
                state = state
            )
        kafkaPublisher.publish(
            topic = kafkaProperties.userProfileRequestConfirmationTopic,
            key = userProfileWorkflow.userId.hashCode(), // Using "userId" as key so that all messages belonging to same user is in order
            payload = objectMapper.writeValueAsString(userProfileRequestConfirmationMessage)
        )
    }
}