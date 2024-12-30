package io.anan.eurekaspace.async_boost.infra.repository

import io.anan.eurekaspace.async_boost.domain.model.ChatRoomModel
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChatRoomRepository: ReactiveMongoRepository<ChatRoomModel, String>