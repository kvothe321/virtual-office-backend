package net.kvt_craft.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserDTO(val name: String, val email: String)
