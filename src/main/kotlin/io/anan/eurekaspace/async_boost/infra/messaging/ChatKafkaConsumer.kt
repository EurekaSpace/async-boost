package io.anan.eurekaspace.async_boost.infra.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import reactor.core.publisher.Sinks

@Component
class ChatKafkaConsumer {

    // 채팅방별 메시지 브로드캐스트 관리
    private val roomSinkMap = mutableMapOf<String, Sinks.Many<String>>()

    fun getSink(roomId: String): Sinks.Many<String> =
            roomSinkMap.computeIfAbsent(roomId) { Sinks.many().multicast().onBackpressureBuffer() }

    @KafkaListener(topicPattern = "chat", groupId = "chat-group")
    suspend fun listen(message: ChatMessage, key: String) {
        println("Received Kafka Message: $message")
        val roomSink = getSink(key) // roomId에 해당하는 Sink 조회

        roomSink.tryEmitNext(
                ObjectMapper().writeValueAsString(message)
        ) // 클라이언트들에게 브로드캐스트
    }
}