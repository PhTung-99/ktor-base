package com.example.features.user.repository

import com.example.data.models.BaseResponse
import com.example.data.user.models.User
import io.ktor.http.*
import java.util.UUID

interface UserRepository {
    suspend fun getUserInfo(userId: UUID): Pair<HttpStatusCode, BaseResponse<User?>>
}