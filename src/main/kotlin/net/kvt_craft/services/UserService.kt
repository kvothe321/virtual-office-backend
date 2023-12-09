package net.kvt_craft.services

import net.kvt_craft.dao.UserDAO
import net.kvt_craft.dto.CreateUserDTO
import net.kvt_craft.dto.RegisterDTO
import net.kvt_craft.dto.RegisterResponseDTO
import net.kvt_craft.dto.RequestStatus
import net.kvt_craft.entities.User

class UserService {
    private val userDAO = UserDAO()

    fun getAll(): List<User> = userDAO.getAll()


    fun registerNewUser(user: RegisterDTO): RegisterResponseDTO {
        val messages = mutableListOf<String>()
        if (user.password != user.passwordConfirmation) {
            messages.add("Passwords don't match")
        }

        // TODO others verifications
        if (messages.isNotEmpty()) {
            return RegisterResponseDTO(RequestStatus.ERROR, messages)
        } else {
            userDAO.register(user)
            return RegisterResponseDTO(RequestStatus.SUCCESS, messages)
        }
    }
}