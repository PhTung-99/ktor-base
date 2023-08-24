package com.example.data.user.dao

import com.example.data.user.models.User

interface UserDAO {
    suspend fun countEmailUsed(email:String): Long
    suspend fun createUser(email: String, name: String, password: String): User?
    suspend fun getUserByEmail(email:String): User?

}