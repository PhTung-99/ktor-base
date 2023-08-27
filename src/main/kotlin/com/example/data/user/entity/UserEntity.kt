package com.example.data.user.entity

import org.jetbrains.exposed.dao.id.UUIDTable

object UserEntity : UUIDTable("user") {
    val name = varchar("name", 50)
    val email = varchar("email", 50).uniqueIndex()
    val password = varchar("password", 100)
}