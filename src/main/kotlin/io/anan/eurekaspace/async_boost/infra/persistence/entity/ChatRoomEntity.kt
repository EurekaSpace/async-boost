package io.anan.eurekaspace.async_boost.infra.persistence.entity

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("chat_rooms")
data class ChatRoomEntity(
        val id: String? = null,
        val name: String? = null,
        val userIds: List<String>,
        val messageIds: List<String> = emptyList(),
        val createdAt: LocalDateTime = LocalDateTime.now()
)