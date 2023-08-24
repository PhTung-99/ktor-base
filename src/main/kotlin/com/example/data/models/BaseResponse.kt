package com.example.data.models

import io.ktor.http.*

data class BaseResponse<T>(
    val httpStatusCode: HttpStatusCode,
    val response: HttpResponse<T>,
)
