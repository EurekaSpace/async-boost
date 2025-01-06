package io.anan.eurekaspace.async_boost.adapter.websocket.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import java.util.concurrent.ConcurrentHashMap

@Component
class ChatWebSocketHandler : WebSocketHandler {

    private val roomMap = ConcurrentHashMap<String, Sinks.Many<String>>()

    override fun handle(session: WebSocketSession): Mono<Void> {
        val roomId = session.handshakeInfo.uri.path.split("/").last()
        val roomSink = roomMap.computeIfAbsent(roomId) { Sinks.many().multicast().onBackpressureBuffer() }

        val input = session.receive()
                .map { it.payloadAsText }
                .doOnNext { message ->
                    println("Room [$roomId] Received: $message")
                    roomSink.tryEmitNext(message)
                }

        val output = roomSink.asFlux()
                .map(session::textMessage)
                .doOnNext { println("Room [$roomId] Sent: ${it.payload}") }

        return session.send(output).and(input.then())
    }
}