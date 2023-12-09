package net.kvt_craft.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDTO(
    val status: RequestStatus,
    val message: List<String>
)

enum class RequestStatus {
    SUCCESS, ERROR
}
