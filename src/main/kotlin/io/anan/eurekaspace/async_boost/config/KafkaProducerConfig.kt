package io.anan.eurekaspace.async_boost.config

import io.anan.eurekaspace.async_boost.domain.model.ChatMessage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate

@Configuration
class KafkaProducerConfig {
    @Bean
    fun kafkaTemplate(producerFactory: DefaultKafkaProducerFactory<String, ChatMessage>): KafkaTemplate<String, ChatMessage> {
        return KafkaTemplate(producerFactory)
    }
}
