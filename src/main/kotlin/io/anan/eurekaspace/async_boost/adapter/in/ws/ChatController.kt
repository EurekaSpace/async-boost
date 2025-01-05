package io.anan.eurekaspace.async_boost.adapter.`in`.ws

import io.anan.eurekaspace.async_boost.application.port.`in`.ChatUseCase
import io.anan.eurekaspace.async_boost.domain.model.ChatMessageModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class ChatController(
        private val chatUseCase: ChatUseCase
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    // RSocket 라우트: "message"
    // Request-Response, Fire-and-Forget, Stream 등 다양한 방식으로 처리 가능
    @MessageMapping("message")
    suspend fun sendMessage(chat: ChatMessageModel) {
        log.info("Received message >>>>>>>>>>>>>>>>>>>>>>>>>>>  $chat")

        chatUseCase.sendMessage(chat)
    }

    // Request-Response (1:1 요청-응답)
    @MessageMapping("request-response")
    suspend fun requestResponse(message: String): String {
        println("request-response Message $message")
        return "Echo: $message"
    }

    // Fire-and-Forget (단방향 전송)
    @MessageMapping("fire-and-forget")
    suspend fun fireAndForget(message: String) {
        println("Fire-and-Forget Received: $message")
    }

    // Request-Stream (스트림 전송)
    @MessageMapping("request-stream")
    fun requestStream(message: String): Flow<String> = flow {
        repeat(5) {
            emit("Stream Message $it: $message")
            println("Stream Message $it: $message")
            delay(1000)
        }
    }
}
