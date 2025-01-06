package io.anan.eurekaspace.async_boost.infra.messaging

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import reactor.core.publisher.Sinks

@Component
class ChatKafkaConsumer {

    // 채팅방별 메시지 브로드캐스트 관리
    private val roomSinkMap = mutableMapOf<String, Sinks.Many<String>>()

    fun getSink(roomId: String): Sinks.Many<String> =
            roomSinkMap.computeIfAbsent(roomId) { Sinks.many().multicast().onBackpressureBuffer() }

    @KafkaListener(topicPattern = "chat-*", groupId = "chat-group")
    suspend fun listen(record: ConsumerRecord<String, String>, ack: Acknowledgment) {
        try {
            // 키(key)와 메시지(value) 추출
            val key = record.key() ?: throw IllegalArgumentException("Key is null!")
            val message = record.value()

            println("Received Kafka Message: $message for room: $key")

            // 채팅방 Sink 조회
            val roomSink = getSink(key)

            // 메시지를 브로드캐스트
            roomSink.tryEmitNext(message)

            // 수동 ACK 처리
            ack.acknowledge()
        } catch (e: Exception) {
            println("Error processing message: ${e.message}")
            e.printStackTrace()
        }
    }
}
