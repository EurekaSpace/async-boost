package io.anan.eurekaspace.async_boost.infra.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration

@Configuration
class MongoConfig : AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName(): String {
        return "asyncboostdb"
    }

    @Bean
    override fun fieldNamingStrategy() = SnakeCaseFieldNamingStrategy()
}