package io.anan.eurekaspace.async_boost.infra.persistence.repository

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.domain.repository.ChatRepository
import io.anan.eurekaspace.async_boost.infra.persistence.mapper.toEntity
import io.anan.eurekaspace.async_boost.infra.persistence.mapper.toModel
import org.springframework.stereotype.Repository

@Repository
class ChatRepositoryImpl(
    private val chatMessageMongoRepository: ChatMessageMongoRepository
): ChatRepository {
    override suspend fun saveMessage(message: ChatMessage): ChatMessage {
        val savedEntity = chatMessageMongoRepository.insert(message.toEntity())
        return savedEntity.toModel()
    }
}
