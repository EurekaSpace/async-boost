package io.anan.eurekaspace.async_boost.domain.port

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage

interface ChatMessageMQPort {
    fun publishMessage(chatMessage: ChatMessage)
}