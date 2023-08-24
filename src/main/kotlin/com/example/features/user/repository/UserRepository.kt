package com.example.features.user.repository

import com.example.features.user.models.User
import com.example.features.user.models.requests.SignupRequest

interface UserRepository {
    suspend fun signUp(signupRequest: SignupRequest): User?
}