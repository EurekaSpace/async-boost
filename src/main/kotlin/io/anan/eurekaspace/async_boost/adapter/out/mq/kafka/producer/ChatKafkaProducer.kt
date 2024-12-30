package io.anan.eurekaspace.async_boost.adapter.out.mq.kafka.producer

import io.anan.eurekaspace.async_boost.application.port.out.mq.ChatMqPort
import io.anan.eurekaspace.async_boost.domain.model.ChatMessageModel
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ChatKafkaProducer(
        private val kafkaTemplate: KafkaTemplate<String, ChatMessageModel>
): ChatMqPort {
    override fun pubMessage(message: ChatMessageModel) {
        kafkaTemplate.send("chat", message)
    }
}