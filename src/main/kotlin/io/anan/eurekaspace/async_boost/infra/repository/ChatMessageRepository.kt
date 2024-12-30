package io.anan.eurekaspace.async_boost.infra.repository

import io.anan.eurekaspace.async_boost.domain.model.ChatMessageModel
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChatMessageRepository: ReactiveMongoRepository<ChatMessageModel, String>