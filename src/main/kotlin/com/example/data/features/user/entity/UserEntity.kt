package com.example.data.features.user.entity

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

object UserEntity : UUIDTable("user") {
    val name = varchar("name", 50)
    val email = varchar("email", 50).uniqueIndex()
    val password = varchar("password", 100)
    val createAtUTC = timestamp("created_at_UTC").clientDefault { Instant.now() }
    val isDeleted = bool("is_deleted").clientDefault { false }
}