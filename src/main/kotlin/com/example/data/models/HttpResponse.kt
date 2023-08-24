package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class HttpResponse<T>(
    val messageCode: String,
    val data: T?,
)
