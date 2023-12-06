package demo.craft.common.domain.kafka.impl

import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.Product
import demo.craft.common.domain.kafka.AbstractKafkaRequestMessage
import demo.craft.common.domain.kafka.enums.MessageContext
import java.sql.Timestamp

data class UserProfileValidationRequestMessage(
    override val userId: String,
    override val requestId: String,
    override val operation: Operation,
    override val userProfileMessage: UserProfileMessage,
    override val timestamp: Timestamp,
    val products: List<Product>, // List of products user with active subscription
) : AbstractKafkaRequestMessage(
    userId = userId,
    requestId = requestId,
    operation = operation,
    userProfileMessage = userProfileMessage,
    timestamp = timestamp,
    messageContext = MessageContext.USER_PROFILE_VALIDATION_REQUEST
)