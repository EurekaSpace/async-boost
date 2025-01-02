package io.anan.eurekaspace.async_boost.adapter.`in`.ws

import io.anan.eurekaspace.async_boost.application.port.`in`.ChatUseCase
import io.anan.eurekaspace.async_boost.domain.model.ChatMessageModel
import org.slf4j.LoggerFactory
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.user.SimpSession
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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
}