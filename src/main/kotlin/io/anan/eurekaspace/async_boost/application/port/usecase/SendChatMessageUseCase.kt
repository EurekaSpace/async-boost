package io.anan.eurekaspace.async_boost.application.port.usecase

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.domain.port.ChatMessageMQPort
import io.anan.eurekaspace.async_boost.domain.repository.ChatMessageRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SendChatMessageUseCase(
        private val chatMessageMQPort: ChatMessageMQPort,
        private val chatMessageRepository: ChatMessageRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(message: ChatMessage) {
        val savedMessage = chatMessageRepository.save(message)
        log.info("saved message >>>  $savedMessage")
        chatMessageMQPort.publishMessage(savedMessage)
    }
}
