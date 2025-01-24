package io.anan.eurekaspace.async_boost.adapter.websocket.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

@Component
class ChatWebSocketHandler(
        private val objectMapper: ObjectMapper
): WebSocketHandler {
    private val roomSinkMap = mutableMapOf<String, Sinks.Many<String>>()

    fun getSink(roomId: String): Sinks.Many<String> = roomSinkMap.compute(roomId) { _, existingSink ->
        if (existingSink == null || existingSink.currentSubscriberCount() == 0) {
            // 새로운 Sink 생성
            Sinks.many().multicast().onBackpressureBuffer(10000)
        } else {
            existingSink
        }
    } ?: Sinks.many().multicast().onBackpressureBuffer(10000)

    override fun handle(session: WebSocketSession): Mono<Void> {
        val roomId = session.handshakeInfo.uri.path.split("/").last()
        val sink = getSink(roomId)

        // 클라이언트 → Sink로 메시지 전송
        val input = session.receive()
                .map { it.payloadAsText }
                .doOnNext { message ->
                    val chatMessage = ChatMessage(
                            roomId = roomId,
                            senderId = "user_001",
                            content = message
                    )
                    sink.tryEmitNext(objectMapper.writeValueAsString(chatMessage))
                }

        // Sink → 클라이언트로 메시지 전송
        val output = sink.asFlux()
                .map(session::textMessage)

        return session.send(output).and(input.then())
    }
}
