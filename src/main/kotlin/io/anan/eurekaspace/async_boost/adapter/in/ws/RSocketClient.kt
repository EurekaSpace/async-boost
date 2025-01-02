package io.anan.eurekaspace.async_boost.adapter.`in`.ws

import org.springframework.http.codec.cbor.Jackson2CborDecoder
import org.springframework.http.codec.cbor.Jackson2CborEncoder
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import org.springframework.messaging.rsocket.connectTcpAndAwait
import org.springframework.stereotype.Service

@Service
class MyService(rsocketRequesterBuilder: RSocketRequester.Builder) {

    val strategies = RSocketStrategies.builder()
            .encoders { it.add(Jackson2CborEncoder()) }
            .decoders { it.add(Jackson2CborDecoder()) }
            .build()

    fun someRSocketCall(name: String) {
        val requester = RSocketRequester.builder()
                .rsocketStrategies(strategies)
                .connectTcpAndAwait("localhost", 7000)
    }

}