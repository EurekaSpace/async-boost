package io.anan.eurekaspace.async_boost.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfig {
    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
                .registerModule(JavaTimeModule()) // 시간 직렬화 모듈 추가
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // ISO-8601 형식 사용
    }
}
