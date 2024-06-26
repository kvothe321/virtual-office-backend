package net.kvt_craft.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.kvt_craft.controllers.realTimePositionController
import net.kvt_craft.controllers.userController

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/login"){
            call.respondText("Logare cu succes")
        }

        get("/register"){
            call.respondText("Inregistrare cu succes")
        }
        userController()
        realTimePositionController()
    }
}
