package com.example.data.features.user.dao

import com.example.data.features.user.models.User
import com.example.data.features.user.models.UserToken
import java.util.UUID

interface UserDAO {
    suspend fun emailUsed(email:String): Boolean
    suspend fun createUser(email: String, name: String, password: String, avatar: String?): User?
    suspend fun getUserByEmail(email: String): User?
    suspend fun getUserById(id: UUID): User?
    suspend fun saveRefreshToken(userId: UUID, refreshToken: String): UserToken?

}