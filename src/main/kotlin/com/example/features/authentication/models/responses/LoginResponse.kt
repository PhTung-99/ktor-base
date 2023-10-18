package com.example.features.authentication.models.responses

import com.example.data.features.user.models.User
import com.example.data.models.JWTToken
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: JWTToken,
    val user: User,
)