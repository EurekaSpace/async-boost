package io.anan.eurekaspace.async_boost.adapter.`in`.mq.kafka.consumer

import io.anan.eurekaspace.async_boost.application.port.`in`.ChatUseCase
import io.anan.eurekaspace.async_boost.domain.model.ChatMessageModel
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@KafkaListener(topics = ["chat"])
class ChatKafkaConsumer {
    @Autowired
    private lateinit var chatUseCase: ChatUseCase

    private val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["chat"], groupId = "chat-group")
    suspend fun consume(message: String) {
        log.info("Received message >>>>>>>>>>??>>>>>>>>>>>>>>>>>  $message")
//        chatUseCase.sendMessage(message)
    }
}