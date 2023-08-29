package com.example.features.authentication.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class RefreshRequest(val refreshToken: String)
