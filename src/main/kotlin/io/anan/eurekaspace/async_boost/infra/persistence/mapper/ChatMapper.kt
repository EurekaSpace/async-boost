package io.anan.eurekaspace.async_boost.infra.persistence.mapper

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.infra.persistence.entity.ChatMessageEntity

fun ChatMessage.toEntity() = ChatMessageEntity(
    id = id,
    roomId = roomId,
    senderId = senderId,
    content = content,
    timestamp = timestamp
)
fun ChatMessageEntity.toModel() = ChatMessage(
    id = id,
    roomId = roomId,
    senderId = senderId,
    content = content,
    timestamp = timestamp
)
