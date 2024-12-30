package io.anan.eurekaspace.async_boost.adapter.`in`.ws

import io.anan.eurekaspace.async_boost.application.port.`in`.ChatUseCase
import io.anan.eurekaspace.async_boost.domain.model.ChatMessageModel
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.user.SimpSession
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(
        private val chatUseCase: ChatUseCase
) {
    @MessageMapping("/ws/chat/{chatRoomId}")
    suspend fun sendMessage(
            msg: String,
            headers: MessageHeaders,
            @DestinationVariable chatRoomId: String
    ) {
        chatUseCase.sendMessage(
                ChatMessageModel(
                    roomId = chatRoomId,
                    sender = headers["sender"] as String,
                    message = msg
                )
        )
    }
}