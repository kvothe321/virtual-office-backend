package net.kvt_craft.services

import net.kvt_craft.dao.UserDAO
import net.kvt_craft.dto.*
import net.kvt_craft.entities.User

class UserService {
    private val userDAO = UserDAO()

    fun getAll(): List<User> = userDAO.getAll()


    fun registerNewUser(user: RegisterDTO): RegisterResponseDTO {
        val messages = mutableListOf<String>()
        if (user.password != user.passwordConfirmation) {
            messages.add("Passwords don't match")
        }

        // TODO: others verifications
        if (messages.isNotEmpty()) {
            return RegisterResponseDTO(RequestStatus.ERROR, messages)
        } else {
            userDAO.register(user)
            return RegisterResponseDTO(RequestStatus.SUCCESS, messages)
        }
    }

    private fun findUserByEmail(email: String): LoginDTO? = userDAO.findUserByEmail(email)

    private fun isPasswordCorrect(user: LoginDTO): Boolean = userDAO.isPasswordCorrect(user)


    fun loginUser(user: LoginDTO): LoginResponseDTO {
        val messages = mutableListOf<String>()

        val foundUser = findUserByEmail(user.email)
        if (foundUser == null || isPasswordCorrect(user).not()) {
            messages.add("Wrong username or password")
        }

        return if (messages.isNotEmpty()) {
            LoginResponseDTO(RequestStatusLogIn.ERROR, messages)
        } else {
            LoginResponseDTO(RequestStatusLogIn.SUCCESS, messages)
        }
    }
}