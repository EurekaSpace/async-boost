package io.anan.eurekaspace.async_boost.domain.model

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class ChatRoomModel(
        val id: String? = null,
        val name: String,
        val users: List<UserModel>,
        val messages: List<ChatMessageModel>,
        val createdAt: LocalDateTime = LocalDateTime.now()
)