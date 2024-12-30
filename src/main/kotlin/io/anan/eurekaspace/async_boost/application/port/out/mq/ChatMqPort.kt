package io.anan.eurekaspace.async_boost.application.port.out.mq

import io.anan.eurekaspace.async_boost.domain.model.ChatMessageModel

interface ChatMqPort {
    fun pubMessage(message: ChatMessageModel)
}