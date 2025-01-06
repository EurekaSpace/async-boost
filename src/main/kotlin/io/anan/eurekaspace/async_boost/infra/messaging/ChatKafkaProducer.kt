package io.anan.eurekaspace.async_boost.infra.messaging

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.domain.port.ChatMessageMQPort
import kotlinx.coroutines.future.await
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ChatKafkaProducer(private val kafkaTemplate: KafkaTemplate<String, ChatMessage>) : ChatMessageMQPort {

    override suspend fun publishMessage(chatMessage: ChatMessage) {
        kafkaTemplate.send(
                "chat",
                chatMessage.roomId,
                chatMessage
        ).await()
    }
}