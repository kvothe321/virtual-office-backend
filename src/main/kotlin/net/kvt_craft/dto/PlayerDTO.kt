package net.kvt_craft.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlayerDTO(
    val id: Int,
    val name: String
)