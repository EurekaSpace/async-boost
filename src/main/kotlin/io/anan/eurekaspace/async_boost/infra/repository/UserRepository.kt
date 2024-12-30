package io.anan.eurekaspace.async_boost.infra.repository

import io.anan.eurekaspace.async_boost.domain.model.UserModel
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository: ReactiveMongoRepository<UserModel, String>