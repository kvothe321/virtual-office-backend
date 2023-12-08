package net.kvt_craft.dao

import net.kvt_craft.dto.CreateUserDTO
import net.kvt_craft.entities.User
import org.jetbrains.exposed.sql.transactions.transaction

class UserDAO {
    fun getAll(): List<User> = transaction {
        User.all().toList()
    }

    fun add(user: CreateUserDTO) = transaction {
        User.new {
            name = user.name
            email = user.email
        }
    }
}