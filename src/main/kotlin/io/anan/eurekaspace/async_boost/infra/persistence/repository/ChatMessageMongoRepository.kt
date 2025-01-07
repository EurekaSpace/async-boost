package io.anan.eurekaspace.async_boost.infra.persistence.repository

import io.anan.eurekaspace.async_boost.infra.persistence.entity.ChatMessageEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChatMessageMongoRepository: ReactiveMongoRepository<ChatMessageEntity, String> {
    suspend fun insert(message: ChatMessageEntity): ChatMessageEntity
}
