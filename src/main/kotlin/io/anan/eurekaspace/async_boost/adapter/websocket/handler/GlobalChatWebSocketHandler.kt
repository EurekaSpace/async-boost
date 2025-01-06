package io.anan.eurekaspace.async_boost.adapter.websocket.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

@Component
class GlobalChatWebSocketHandler : WebSocketHandler {

    private val noticeSink = Sinks.many().multicast().onBackpressureBuffer<String>()

    override fun handle(session: WebSocketSession): Mono<Void> {
        val input = session.receive()
                .map { it.payloadAsText }
                .doOnNext { message ->
                    println("Global Chat Received: $message")
                    noticeSink.tryEmitNext(message)
                }

        val output = noticeSink.asFlux()
                .map(session::textMessage)
                .doOnNext { println("Global Chat Sent: ${it.payload}") }

        return session.send(output).and(input.then())
    }

    fun broadcastGlobalChat(message: String) {
        println("Broadcasting Global Chat: $message")
        noticeSink.tryEmitNext(message)
    }
}