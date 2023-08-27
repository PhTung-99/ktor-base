package com.example.features.authentication.repository

import com.example.data.models.BaseResponse
import com.example.data.user.models.User
import com.example.features.authentication.models.requests.LoginRequest
import com.example.features.authentication.models.requests.SignupRequest
import com.example.features.authentication.models.responses.LoginResponse
import io.ktor.http.*


interface AuthenticationRepository {
    suspend fun signup(signupRequest: SignupRequest): Pair<HttpStatusCode,BaseResponse<User?>>
    suspend fun login(loginRequest: LoginRequest): Pair<HttpStatusCode,BaseResponse<LoginResponse?>>
}