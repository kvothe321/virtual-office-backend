package net.kvt_craft.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.kvt_craft.dto.LoginDTO
import net.kvt_craft.dto.RegisterDTO
import net.kvt_craft.dto.RequestStatus
import net.kvt_craft.dto.RequestStatusLogIn
import net.kvt_craft.entities.toDTO
import net.kvt_craft.services.UserService

fun Route.userController() {
    val userService = UserService()

    get("/users") {
        val users = userService.getAll().map { it.toDTO() }
        call.respond(users)
    }

    post("/register") {
        val receivedRegisterBody = call.receive<RegisterDTO>()

        val registerResponse = userService.registerNewUser(receivedRegisterBody)

        when (registerResponse.status) {
            RequestStatus.SUCCESS -> call.respond(HttpStatusCode.Created, "User registered successfully")
            RequestStatus.ERROR -> call.respond(HttpStatusCode.BadRequest, registerResponse)
        }
        call.respond(HttpStatusCode.Created, receivedRegisterBody.toString())
    }

    post("/login") {
        val receivedLogInBody = call.receive<LoginDTO>()

        val loginResponse = userService.loginUser(receivedLogInBody)

        when (loginResponse.status) {
            RequestStatusLogIn.SUCCESS -> call.respond(HttpStatusCode.OK, "User log in successful")
            RequestStatusLogIn.ERROR -> call.respond(HttpStatusCode.Unauthorized, loginResponse)
        }
    }
}
