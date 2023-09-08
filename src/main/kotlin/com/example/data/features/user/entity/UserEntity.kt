package com.example.data.features.user.entity


import com.example.data.database.datetypes.timestampWithTimeZone
import org.jetbrains.exposed.dao.id.UUIDTable
import java.time.Instant


object UserEntity : UUIDTable("user") {
    val name = varchar("name", 50)
    val email = varchar("email", 50).uniqueIndex()
    val password = varchar("password", 100)
    val avatar = varchar("avatar", 100).nullable()
    val createAtUTC = timestampWithTimeZone("created_at_UTC").default(Instant.now())
    val isDeleted = bool("is_deleted").default(defaultValue = false)
}