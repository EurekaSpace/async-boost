package io.anan.eurekaspace.async_boost.domain.model

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
        val id: String? = null,
        val username: String,
)
