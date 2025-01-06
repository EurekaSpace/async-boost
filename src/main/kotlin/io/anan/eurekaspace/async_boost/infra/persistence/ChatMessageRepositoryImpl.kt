package io.anan.eurekaspace.async_boost.infra.persistence

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import io.anan.eurekaspace.async_boost.domain.repository.ChatMessageRepository
import org.springframework.stereotype.Repository

@Repository
class ChatMessageRepositoryImpl(
    private val chatMessageMongoRepository: ChatMessageMongoRepository
): ChatMessageRepository {
    override fun save(message: ChatMessage): ChatMessage = chatMessageMongoRepository.save(message)

}