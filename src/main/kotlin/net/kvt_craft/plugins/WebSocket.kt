package net.kvt_craft.plugins

import io.ktor.server.application.*
import io.ktor.server.websocket.*


@Suppress("unused") // Referenced in application.yaml
fun Application.configureWebSocket() {
    install(WebSockets)
}
