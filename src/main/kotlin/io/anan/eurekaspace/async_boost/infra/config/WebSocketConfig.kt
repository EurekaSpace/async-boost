package io.anan.eurekaspace.async_boost.infra.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic")
        config.setApplicationDestinationPrefixes("/app")
    }

    override fun registerStompEndpoints(registry: org.springframework.web.socket.config.annotation.StompEndpointRegistry) {
        registry
                .addEndpoint(*listOf("/ws/chat/*").toTypedArray())
                .setAllowedOriginPatterns("*")
//                .withSockJS()


    }
}
