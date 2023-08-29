package com.example.features.authentication.models.responses

import com.example.authentication.JWTUtils
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
    val expiredAt: Int = JWTUtils.validityInMs,
    val refreshToken: String,
    val refreshExpiredAt: Int = JWTUtils.validityRefreshInMs,
)