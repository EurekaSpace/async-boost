package io.anan.eurekaspace.async_boost.domain.port

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage

interface ChatMessageMQPort {
    suspend fun publishMessage(chatMessage: ChatMessage)
}
