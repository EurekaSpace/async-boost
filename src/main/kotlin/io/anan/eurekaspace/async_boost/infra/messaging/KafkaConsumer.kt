package io.anan.eurekaspace.async_boost.infra.messaging

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {

    @KafkaListener(topics = ["chat-messages"], groupId = "chat-group")
    suspend fun listen(message: String) {
        println("Received Kafka Message: $message")
        // 메시지 처리 로직 추가
    }
}