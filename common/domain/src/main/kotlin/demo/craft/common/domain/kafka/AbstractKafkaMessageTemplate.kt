package demo.craft.common.domain.kafka

import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.kafka.enums.MessageContext
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import java.sql.Timestamp

/**
 * This class defines the generic template of the kafka messages which is needed in the system.
 */
abstract class AbstractKafkaRequestMessage(
    open val userId: String,
    open val requestId: String,
    open val userProfileMessage: UserProfileMessage,
    open val operation: Operation,
    open val timestamp: Timestamp,
    open val messageContext: MessageContext
)

abstract class AbstractKafkaCallbackMessage(
    open val userId: String,
    open val requestId: String,
    open val timestamp: Timestamp,
    open val failureReason: String?, // Failure reason can be set as per the context of the message (if available)
    open val messageContext: MessageContext

)