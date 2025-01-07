package io.anan.eurekaspace.async_boost.infra.persistence.mapper

import io.anan.eurekaspace.async_boost.domain.model.User
import io.anan.eurekaspace.async_boost.infra.persistence.entity.UserEntity

fun User.toEntity() = UserEntity(
    id = id,
    username = username
)