package io.anan.eurekaspace.async_boost.infra.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.domain.port.ChatMessageMQPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, String>) : ChatMessageMQPort {

    override fun publishMessage(chatMessage: ChatMessage) {
        kafkaTemplate.send(
                "chat-messages",
                chatMessage.roomId,
                ObjectMapper().writeValueAsString(chatMessage)
        ).get()
    }
}