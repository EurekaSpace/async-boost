package io.anan.eurekaspace.async_boost.adapter.websocket.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.anan.eurekaspace.async_boost.application.usecase.SendChatMessageUseCase
import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.infra.messaging.ChatKafkaConsumer
import kotlinx.coroutines.*
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import java.util.concurrent.Executors

@Component
class ChatWebSocketHandlerWithPersist(
        private val sendChatMessageUseCase: SendChatMessageUseCase,
        private val scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(100).asCoroutineDispatcher()),
        private val objectMapper: ObjectMapper,
) : WebSocketHandler {

    // Sink를 사용해 메시지 스트림 생성 (ReplaySink로 모든 구독자에게 메시지 브로드캐스트)
    private val sink = Sinks.many().multicast().onBackpressureBuffer<String>(10000)

    override fun handle(session: WebSocketSession): Mono<Void> {
        val roomId = session.handshakeInfo.uri.path.split("/").last()

        // 클라이언트 → Sink로 메시지 전송
        val input = session.receive()
                .map { it.payloadAsText }
                .doOnNext { message ->
                    val chatMessage = ChatMessage(
                            roomId = roomId,
                            senderId = "user_001",
                            content = message
                    )
                    scope.launch {
                        val savedMessage = sendChatMessageUseCase.executeWithoudKafka(chatMessage)
                        sink.tryEmitNext(objectMapper.writeValueAsString(savedMessage))
                    }
                }

        // Sink → 클라이언트로 메시지 전송
        val output = sink.asFlux()
                .map(session::textMessage)

        return session.send(output).and(input.then())
    }
}
