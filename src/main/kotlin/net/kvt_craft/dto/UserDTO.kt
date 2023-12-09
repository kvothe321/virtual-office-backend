package net.kvt_craft.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(val id: Int, val email: String, val password: String)

