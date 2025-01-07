package io.anan.eurekaspace.async_boost.infra.persistence.entity

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("chat_messages")
data class ChatMessageEntity(
        val id: String? = null,
        val roomId: String,
        val senderId: String,
        val content: String,
        val timestamp: LocalDateTime = LocalDateTime.now()
)