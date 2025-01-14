package io.anan.eurekaspace.async_boost.application.usecase

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.domain.port.ChatMessageMQPort
import io.anan.eurekaspace.async_boost.domain.repository.ChatRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SendChatMessageUseCase(
        private val chatMessageMQPort: ChatMessageMQPort,
        private val chatRepository: ChatRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(message: ChatMessage) {
        val savedMessage = chatRepository.saveMessage(message)
        log.info("saved message >>>  $savedMessage")
        chatMessageMQPort.publishMessage(savedMessage)
    }

    suspend fun executeWithoudKafka(message: ChatMessage): ChatMessage {
        val savedMessage = chatRepository.saveMessage(message)
        log.info("saved message >>>  $savedMessage")
        return savedMessage
    }
}
