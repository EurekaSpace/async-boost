package io.anan.eurekaspace.async_boost.domain.model

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class ChatMessage(
        val id: String? = null,
        val roomId: String,
        val sender: String,
        val message: String,
        val timestamp: LocalDateTime = LocalDateTime.now()
)