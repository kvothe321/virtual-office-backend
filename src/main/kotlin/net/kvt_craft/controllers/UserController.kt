package net.kvt_craft.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.kvt_craft.dto.RegisterDTO
import net.kvt_craft.dto.RequestStatus
import net.kvt_craft.entities.toDTO
import net.kvt_craft.services.UserService

fun Route.userController() {
    val userService = UserService()

    get("/users") {
        val users = userService.getAll().map { it.toDTO() }
        call.respond(users)
    }

    /*  post("/user") {
          val createUserRequest = call.receive<CreateUserDTO>()
          val createdUser = userService.add(createUserRequest)
          call.respond(HttpStatusCode.Created, createdUser.toDTO())
      }*/

    post("/register") {
        val receivedRegisterBody = call.receive<RegisterDTO>()

        val registerResponse = userService.registerNewUser(receivedRegisterBody)

        when (registerResponse.status) {
            RequestStatus.SUCCESS -> call.respond(HttpStatusCode.Created, "User registered successfully")
            RequestStatus.ERROR -> call.respond(HttpStatusCode.BadRequest, registerResponse)
        }
        call.respond(HttpStatusCode.Created, receivedRegisterBody.toString())
    }
}
