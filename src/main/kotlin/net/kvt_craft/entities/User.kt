package net.kvt_craft.entities

import net.kvt_craft.dto.LoginDTO
import net.kvt_craft.dto.UserDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object Users : IntIdTable() {
    val email = varchar("name", 255)
    val password = varchar("email", 255)

    fun toDTO(row: ResultRow) = LoginDTO(
        email = row[email],
        password = row[password]
    )

}

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var email by Users.email
    var password by Users.password
}

internal fun User.toDTO() = UserDTO(id.value, email, password)