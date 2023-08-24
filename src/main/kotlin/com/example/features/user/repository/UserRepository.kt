package com.example.features.user.repository

import com.example.data.user.models.User
import com.example.features.authentication.models.SignupRequest

interface UserRepository {
    suspend fun signUp(signupRequest: SignupRequest): User?
}