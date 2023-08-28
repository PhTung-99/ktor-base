package com.example.features.user.repository

import com.example.data.models.BaseResponse
import com.example.data.features.user.dao.UserDAO
import com.example.data.features.user.models.User
import com.example.features.user.constants.UserMessageCode
import io.ktor.http.*
import java.util.*

class UserRepositoryImpl(
    private val userDAO: UserDAO
): UserRepository {

    override suspend fun getUserInfo(userId: UUID): Pair<HttpStatusCode, BaseResponse<User?>> {
        val user = userDAO.getUserById(userId)
        return user?.let {
            return Pair(
                HttpStatusCode.OK,
                BaseResponse(
                    data = user,
                )
            )
        } ?: kotlin.run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = UserMessageCode.USER_NOT_FOUND))
        }
    }
}