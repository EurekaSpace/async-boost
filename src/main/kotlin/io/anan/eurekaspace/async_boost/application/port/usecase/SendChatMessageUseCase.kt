package io.anan.eurekaspace.async_boost.application.port.usecase

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.domain.port.ChatMessageMQPort
import io.anan.eurekaspace.async_boost.domain.repository.ChatMessageRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SendChatMessageUseCase(
        private val chatMessageQueue: ChatMessageMQPort,
        private val chatMessageRepository: ChatMessageRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(message: ChatMessage) {
        log.info("Sending message >>>>>>>>>>>>>>>>>>>>>>>>>>!!>  $message")
        val savedMessage = chatMessageRepository.save(message)
        chatMessageQueue.publishMessage(savedMessage)
    }
}
