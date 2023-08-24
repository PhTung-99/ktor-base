package com.example.features.authentication.repository

import com.example.data.models.BaseResponse
import com.example.data.user.models.User
import com.example.features.authentication.models.LoginRequest
import com.example.features.authentication.models.SignupRequest


interface AuthenticationRepository {
    suspend fun signup(signupRequest: SignupRequest): BaseResponse<User?>
    suspend fun login(loginRequest: LoginRequest): BaseResponse<User?>
}