package demo.craft.common.domain.kafka

import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.State
import java.sql.Timestamp

data class UserProfileRequestConfirmationMessage(
    override val userId: String,
    override val requestId: String,
    override val timestamp: Timestamp,
    override val failureReason: String?,
    val operation: Operation,
    val state: State,
) : AbstractKafkaCallbackMessage(
    userId = userId,
    requestId = requestId,
    timestamp = timestamp,
    failureReason = failureReason,
    messageContext = MessageContext.USER_PROFILE_REQUEST_CONFIRMATION
)

