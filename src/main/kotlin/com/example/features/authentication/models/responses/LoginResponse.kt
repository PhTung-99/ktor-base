package com.example.features.authentication.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
    val expiredAt: Int = 3600000,
    val refreshToken: String,
    val refreshExpiredAt: Int = 86_400_000
)