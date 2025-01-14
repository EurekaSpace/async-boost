package io.anan.eurekaspace.async_boost.domain.model

import java.time.LocalDateTime

data class ChatMessage(
        val id: String? = null,
        val roomId: String,
        val senderId: String,
        val content: String,
        val timestamp: LocalDateTime = LocalDateTime.now()
)