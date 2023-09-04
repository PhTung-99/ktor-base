package com.example.data.features.user.models

import com.example.plugin.serializable.custom.*
import kotlinx.serialization.Serializable
import java.time.Instant

import java.util.UUID

@Serializable
data class UserToken(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val refreshToken: String,
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
    @Serializable(with = InstantSerializer::class)
    val createAtUTC: Instant,
)
