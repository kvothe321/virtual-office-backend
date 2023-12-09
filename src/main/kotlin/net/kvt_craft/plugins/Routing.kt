package net.kvt_craft.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.kvt_craft.controllers.userController
import net.kvt_craft.dto.UserDTO
import net.kvt_craft.dto.UserDTO1

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }


        get("/login"){
            val userLogIn = LogInUser("andrei@gmail.com", "12345678")
            val userReceive = call.receive<UserDTO1>()
            if(findUser(userLogIn, userReceive)){
                call.respondText("Autentificare cu succes")
            }
            else
            {
                call.respondText("Autentificare esuata")
            }

        }
        userController()
    }
}

fun findUser(userLogIn: LogInUser, userReceive: UserDTO1): Boolean {
    if(userLogIn.email.equals(userReceive.email) && userLogIn.passsword.equals(userReceive.password)){
        return true
    }
    return false

}

data class LogInUser(
    val email: String,
    val passsword: String
)
