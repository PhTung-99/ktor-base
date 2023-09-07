package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class HealthCheck(
    val database: String,
    val redis: String,
)
