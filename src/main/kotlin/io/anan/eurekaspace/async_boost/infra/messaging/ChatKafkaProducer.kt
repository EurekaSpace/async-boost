package io.anan.eurekaspace.async_boost.infra.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.domain.port.ChatMessageMQPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ChatKafkaProducer(
        private val kafkaTemplate: KafkaTemplate<String, String>,
        private val objectMapper: ObjectMapper
) : ChatMessageMQPort {

    override suspend fun publishMessage(chatMessage: ChatMessage) {
        kafkaTemplate.send(
                "chat",
                chatMessage.roomId,
                objectMapper.writeValueAsString(chatMessage)
        )
    }
}
