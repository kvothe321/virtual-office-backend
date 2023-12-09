package net.kvt_craft.dao

import net.kvt_craft.dto.LoginDTO
import net.kvt_craft.dto.RegisterDTO
import net.kvt_craft.entities.User
import org.jetbrains.exposed.sql.transactions.transaction

class UserDAO {
    fun getAll(): List<User> = transaction {
        User.all().toList()
    }

    fun findUserByEmail(email: String): LoginDTO? {
        val allUsers = getAll()
        val foundUser = allUsers.find { it.email.equals(email) }
        return foundUser?.let { LoginDTO(foundUser.email, foundUser.password) }
    }

    fun register(user: RegisterDTO) = transaction {
        User.new {
            email = user.email
            password = user.password
        }
    }
}