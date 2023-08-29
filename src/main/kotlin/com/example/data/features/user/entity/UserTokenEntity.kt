package com.example.data.features.user.entity

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

object UserTokenEntity: UUIDTable("user_token") {
    val userId = reference("user_id", UserEntity.id)
    val refreshToken = varchar("refresh_token", 500)
    val createAtUTC = timestamp("created_at_UTC").clientDefault { Instant.now() }
}