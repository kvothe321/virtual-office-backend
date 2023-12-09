package net.kvt_craft.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterDTO(
    val email: String,
    val password: String,
    val passwordConfirmation: String
)
