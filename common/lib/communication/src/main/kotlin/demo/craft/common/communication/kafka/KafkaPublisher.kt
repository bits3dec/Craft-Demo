package demo.craft.common.communication.kafka

interface KafkaPublisher {
    /**
     * [topic] : This is the topic to which message is to be sent.
     * [key]: This is used as key to ensure all messages with same key go to the same partition if ordering of message is needed
     * [payload]: This is the message body which is to be sent.
     */
    fun publish(topic: String, key: Int, payload: String)
}