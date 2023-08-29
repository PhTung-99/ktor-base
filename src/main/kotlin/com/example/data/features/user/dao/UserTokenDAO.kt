package com.example.data.features.user.dao


import com.example.data.features.user.models.UserToken
import java.util.UUID

interface UserTokenDAO {
    suspend fun getRefreshTokenByUserId(userId: UUID): UserToken?

}