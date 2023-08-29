package com.example.features.authentication.repository

import com.example.data.models.BaseResponse
import com.example.data.features.user.models.User
import com.example.features.authentication.models.requests.LoginRequest
import com.example.features.authentication.models.requests.SignupRequest
import com.example.features.authentication.models.responses.LoginResponse
import io.ktor.http.*
import java.util.UUID


interface AuthenticationRepository {
    suspend fun signup(signupRequest: SignupRequest): Pair<HttpStatusCode,BaseResponse<User?>>
    suspend fun login(loginRequest: LoginRequest): Pair<HttpStatusCode,BaseResponse<LoginResponse?>>
    suspend fun refreshToken(
        userId: UUID,
        refreshToken: String,
    ): Pair<HttpStatusCode,BaseResponse<LoginResponse?>>
}