package demo.craft.common.domain.kafka

import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.State
import demo.craft.common.domain.enums.TaxType
import java.sql.Timestamp

data class UserProfileRequestMessage(
    override val userId: String,
    override val requestId: String,
    override val operation: Operation,
    override val userProfileMessage: UserProfileMessage,
    override val timestamp: Timestamp,
    val state: State,
) : AbstractKafkaRequestMessage(
    userId = userId,
    requestId = requestId,
    operation = operation,
    userProfileMessage = userProfileMessage,
    timestamp = timestamp,
    messageContext = MessageContext.USER_PROFILE_REQUEST
)

data class AddressMessage(
    val line1: String,
    val line2: String,
    val city: String,
    val state: String,
    val country: String,
    val zip: String
)

data class TaxIdentifierMessage(
    val value: String,
    val type: TaxType,
)

data class UserProfileMessage(
    val companyName: String,
    val legalName: String,
    val businessAddress: AddressMessage,
    val legalAddress: AddressMessage,
    val taxIdentifier: TaxIdentifierMessage,
    val email: String,
    val website: String,
)