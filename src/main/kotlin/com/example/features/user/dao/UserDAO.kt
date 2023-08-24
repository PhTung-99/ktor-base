package com.example.features.user.dao

import com.example.features.user.models.User

interface UserDAO {
    suspend fun getEmailUsed(email:String): Long
    suspend fun createUser(email: String, name: String, password: String): User?
}