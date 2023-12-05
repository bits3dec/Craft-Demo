package demo.craft.common.domain.kafka

import demo.craft.common.domain.enums.Product
import demo.craft.common.domain.enums.State
import java.sql.Timestamp

data class UserProfileValidationConfirmationMessage(
    override val userId: String,
    override val requestId: String,
    override val timestamp: Timestamp,
    override val failureReason: String?, // Set failure reason if available
    val state: State,
    val products: List<Product>, // List of products user with active subscription
) : AbstractKafkaCallbackMessage(
    userId = userId,
    requestId = requestId,
    timestamp = timestamp,
    failureReason = failureReason,
    messageContext = MessageContext.USER_PROFILE_VALIDATION_CONFIRMATION
)