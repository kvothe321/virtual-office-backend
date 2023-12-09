package net.kvt_craft.dto

import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    val status: RequestStatusLogIn,
    val messages: List<String>
)

enum class RequestStatusLogIn{
    SUCCESS, ERROR
}
