package io.anan.eurekaspace.async_boost.infra.config

import io.anan.eurekaspace.async_boost.adapter.websocket.handler.ChatWebSocketHandler
import io.anan.eurekaspace.async_boost.adapter.websocket.handler.GlobalChatWebSocketHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter


@Configuration
class WebSocketConfig {
    @Bean
    fun handlerMapping(chatHandler: ChatWebSocketHandler, globalChatHandler: GlobalChatWebSocketHandler): SimpleUrlHandlerMapping {
        val map = mapOf(
                "/chat/{roomId}" to chatHandler,
                "/chat/global" to globalChatHandler
        )
        return SimpleUrlHandlerMapping(map, 1)
    }

    @Bean
    fun handlerAdapter(): WebSocketHandlerAdapter = WebSocketHandlerAdapter()
}