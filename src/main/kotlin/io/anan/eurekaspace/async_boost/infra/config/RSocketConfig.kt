package io.anan.eurekaspace.async_boost.infra.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketStrategies
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler
import org.springframework.web.util.pattern.PathPatternRouteMatcher


@Configuration
class RSocketConfig {

    // RSocket에서 사용할 전략들(인코더, 디코더, 기타 커스텀 설정)을 정의
    @Bean
    fun rSocketStrategies(): RSocketStrategies {
        return RSocketStrategies.builder()
                // Jackson 모듈, 기타 커스텀 코덱을 등록할 수 있음
                // .encoder(Jackson2JsonEncoder())
                // .decoder(Jackson2JsonDecoder())
                .build()
    }

    // @Controller, @MessageMapping 등을 인식하도록 RSocketMessageHandler 등록
    @Bean
    fun rSocketMessageHandler(strategies: RSocketStrategies): RSocketMessageHandler {
        val messageHandler = RSocketMessageHandler()
        messageHandler.rSocketStrategies = strategies
        messageHandler.routeMatcher = PathPatternRouteMatcher()
        return messageHandler
    }
}