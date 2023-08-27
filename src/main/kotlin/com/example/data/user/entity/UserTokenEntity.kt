package com.example.data.user.entity

import org.jetbrains.exposed.dao.id.UUIDTable

object UserTokenEntity: UUIDTable("user_token") {
    val userId = reference("user_id", UserEntity.id)
    val refreshToken = varchar("refresh_token", 100)
}