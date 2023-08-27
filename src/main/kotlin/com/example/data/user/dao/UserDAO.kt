package com.example.data.user.dao

import com.example.data.user.models.User
import java.util.UUID

interface UserDAO {
    suspend fun emailUsed(email:String): Boolean
    suspend fun createUser(email: String, name: String, password: String): User?
    suspend fun getUserByEmail(email: String): User?
    suspend fun getUserById(id: UUID): User?

}