package io.anan.eurekaspace.async_boost.domain.model

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class ChatRoom(
        val id: String? = null,
        val name: String,
        val users: List<User>,
        val messages: List<ChatMessage>,
        val createdAt: LocalDateTime = LocalDateTime.now()
)