package io.anan.eurekaspace.async_boost.domain.repository

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage

interface ChatRepository {
    suspend fun saveMessage(message: ChatMessage): ChatMessage
}