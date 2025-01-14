package io.anan.eurekaspace.async_boost.infra.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoroutineScopeConfig {

    @Bean
    fun coroutineScope(): CoroutineScope {
        // Define a custom CoroutineScope with a dispatcher
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}