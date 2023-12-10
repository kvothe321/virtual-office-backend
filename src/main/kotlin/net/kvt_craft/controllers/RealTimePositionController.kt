package net.kvt_craft.controllers

import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import io.ktor.websocket.Frame.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import net.kvt_craft.dto.PlayerDTO
import java.util.*

fun Route.realTimePositionController() {
    val connections = Collections.synchronizedSet<DefaultWebSocketServerSession>(LinkedHashSet())

    webSocket("/myws/real-time-position") {
        connections += this
        try {
            for (frame in incoming) {
                frame as? Text ?: continue
                val text = frame.readText()
                val position = decodeFromString<PlayerDTO>(text)

                val message = Json.encodeToString(position)
                connections.filter { it != this }.forEach {
                    it.send(Text(message))
                }
            }
        } finally {
            connections -= this
        }
    }
}