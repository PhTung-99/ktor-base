package com.example.data.features.user.models

import com.example.plugin.serializable.custom.*
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class User(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String,
    val email: String,
    var password: String? = null,
    val avatar: String,
    @Serializable(with = InstantSerializer::class)
    val createAtUTC: Instant,
    val isDeleted: Boolean,
)