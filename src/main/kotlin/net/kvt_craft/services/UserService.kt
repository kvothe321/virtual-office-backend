package net.kvt_craft.services

import net.kvt_craft.dao.UserDAO
import net.kvt_craft.dto.CreateUserDTO
import net.kvt_craft.entities.User

class UserService {
    private val userDAO = UserDAO()

    fun getAll(): List<User> = userDAO.getAll()

    fun add(user: CreateUserDTO): User = userDAO.add(user)
}