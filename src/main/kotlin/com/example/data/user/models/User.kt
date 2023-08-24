package com.example.data.user.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long? = null,
    val name: String,
    val email: String,
    var password: String? = null,
)