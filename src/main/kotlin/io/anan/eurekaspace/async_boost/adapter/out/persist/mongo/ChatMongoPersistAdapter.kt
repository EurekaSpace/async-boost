package io.anan.eurekaspace.async_boost.adapter.out.persist.mongo

import io.anan.eurekaspace.async_boost.application.port.out.persist.ChatPersistPort
import io.anan.eurekaspace.async_boost.domain.model.ChatMessageModel
import org.springframework.stereotype.Component

@Component
class ChatMongoPersistAdapter: ChatPersistPort {
    override fun saveMessage(message: ChatMessageModel) {
        TODO("Not yet implemented")
    }
}