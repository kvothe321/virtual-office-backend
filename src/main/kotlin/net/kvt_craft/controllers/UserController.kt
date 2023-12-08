package net.kvt_craft.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.kvt_craft.dto.CreateUserDTO
import net.kvt_craft.dto.UserDTO
import net.kvt_craft.entities.toDTO
import net.kvt_craft.services.UserService

fun Route.userController() {
    val userService = UserService()

    get("/users") {
        val users = userService.getAll().map { UserDTO(it.id.value, it.name, it.email) }
        call.respond(users)
    }

    post("/user") {
        val createUserRequest = call.receive<CreateUserDTO>()
        val createdUser = userService.add(createUserRequest)
        call.respond(HttpStatusCode.Created, createdUser.toDTO())
    }
}
