package io.anan.eurekaspace.async_boost.adapter.`in`.ws

import io.anan.eurekaspace.async_boost.application.port.`in`.ChatUseCase
import io.anan.eurekaspace.async_boost.domain.model.ChatMessageModel
import org.slf4j.LoggerFactory
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.user.SimpSession
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(
        private val chatUseCase: ChatUseCase
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @MessageMapping("/message")
    suspend fun sendMessage(
            msg: String,
    ) {
        log.info("Received message >>>>>>>>>>>>>>>>>>>>>>>>>>>  $msg")

        chatUseCase.sendMessage(
                ChatMessageModel(
                    roomId = "123",
                    sender = "456",
                    message = msg
                )
        )
    }
}