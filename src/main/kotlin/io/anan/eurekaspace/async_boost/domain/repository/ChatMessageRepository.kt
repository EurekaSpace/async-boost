package io.anan.eurekaspace.async_boost.domain.repository

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage

interface ChatMessageRepository {
    suspend fun save(message: ChatMessage): ChatMessage
}
