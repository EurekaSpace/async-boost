package io.anan.eurekaspace.async_boost.infra.persistence.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class UserEntity(
        val id: String? = null,
        val username: String,
)
