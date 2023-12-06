package demo.craft.common.domain.kafka.impl

import demo.craft.common.domain.enums.State
import demo.craft.common.domain.kafka.AbstractKafkaCallbackMessage
import demo.craft.common.domain.kafka.enums.MessageContext
import java.sql.Timestamp

data class UserProfileValidationConfirmationMessage(
    override val userId: String,
    override val requestId: String,
    override val timestamp: Timestamp,
    override val failureReason: String?, // Set failure reason if available
    val state: State
) : AbstractKafkaCallbackMessage(
    userId = userId,
    requestId = requestId,
    timestamp = timestamp,
    failureReason = failureReason,
    messageContext = MessageContext.USER_PROFILE_VALIDATION_CONFIRMATION
)