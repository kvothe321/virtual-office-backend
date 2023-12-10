package net.kvt_craft

import io.ktor.server.application.*
import net.kvt_craft.plugins.configureContentNegotiation
import net.kvt_craft.plugins.configureDatabase
import net.kvt_craft.plugins.configureRouting
import net.kvt_craft.plugins.configureWebSocket

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.yaml
fun Application.module() {
    configureContentNegotiation()
    configureWebSocket()
    configureDatabase()
    configureRouting()
}
