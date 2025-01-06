package io.anan.eurekaspace.async_boost.application.port.out.persist

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage

interface ChatPersistPort {
    fun saveMessage(message: ChatMessage)
}