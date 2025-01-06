package io.anan.eurekaspace.async_boost.application.port.usecase

import io.anan.eurekaspace.async_boost.application.port.out.mq.ChatMqPort
import io.anan.eurekaspace.async_boost.application.port.out.persist.ChatPersistPort
import io.anan.eurekaspace.async_boost.domain.model.ChatMessageModel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ChatService(
        private val chatMqPort: ChatMqPort,
        private val chatPersistPort: ChatPersistPort
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    suspend fun sendMessage(message: ChatMessageModel) {
        log.info("Sending message >>>>>>>>>>>>>>>>>>>>>>>>>>!!>  $message")
        chatPersistPort.saveMessage(message)
        chatMqPort.pubMessage(message)
    }
}
