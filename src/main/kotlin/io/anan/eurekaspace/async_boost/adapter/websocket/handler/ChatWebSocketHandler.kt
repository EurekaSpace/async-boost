package io.anan.eurekaspace.async_boost.adapter.websocket.handler

import io.anan.eurekaspace.async_boost.application.port.usecase.SendChatMessageUseCase
import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.infra.messaging.ChatKafkaConsumer
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class ChatWebSocketHandler(
        private val sendChatMessageUseCase: SendChatMessageUseCase,
        private val chatKafkaConsumer: ChatKafkaConsumer,
) : WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> {
        val roomId = session.handshakeInfo.uri.path.split("/").last()

        // 클라이언트 → Kafka로 메시지 전송
        val input = session.receive()
                .map { it.payloadAsText }
                .doOnNext { message ->
                    val chatMessage = ChatMessage(
                            roomId = roomId,
                            sender = "user",
                            message = message
                    )
                    runBlocking {
                        sendChatMessageUseCase.execute(chatMessage)
                    }
                }

        // Kafka → 클라이언트로 메시지 전송
        val output = chatKafkaConsumer.getSink(roomId).asFlux()
                .map(session::textMessage)

        return session.send(output).and(input.then())
    }
}
