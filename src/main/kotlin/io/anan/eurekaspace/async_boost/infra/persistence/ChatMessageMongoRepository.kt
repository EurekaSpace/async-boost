package io.anan.eurekaspace.async_boost.infra.persistence

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChatMessageMongoRepository: ReactiveMongoRepository<ChatMessage, String> {
    suspend fun insert(message: ChatMessage): ChatMessage
}
