package net.kvt_craft.dto

import kotlinx.serialization.Serializable
import net.kvt_craft.entities.User

@Serializable
data class UserDTO(val id: Int, val name: String, val email: String)

