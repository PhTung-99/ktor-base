package com.example.features.authentication.repository

import com.example.authentication.JWTUltis
import com.example.constants.BaseMessageCode
import com.example.data.models.BaseResponse
import com.example.data.user.dao.UserDAO
import com.example.data.user.models.User
import com.example.features.authentication.constants.AuthenticationMessageCode
import com.example.features.authentication.models.requests.LoginRequest
import com.example.features.authentication.models.requests.SignupRequest
import com.example.features.authentication.models.responses.LoginResponse
import io.ktor.http.*
import org.mindrot.jbcrypt.BCrypt

class AuthenticationRepositoryImpl(
    private val userDAO: UserDAO
): AuthenticationRepository {

    private suspend fun isEmailAvailable(email: String): Boolean {
        return !userDAO.emailUsed(email)
    }

    override suspend fun signup(signupRequest: SignupRequest): Pair<HttpStatusCode,BaseResponse<User?>> {
        return if (isEmailAvailable(signupRequest.email)) {
            val hashedPassword = BCrypt.hashpw(signupRequest.password, BCrypt.gensalt())
            val user = userDAO.createUser(
                email = signupRequest.email,
                name = signupRequest.name,
                password = hashedPassword
            )
            return if (user != null) {
                Pair(
                    HttpStatusCode.Created,
                    BaseResponse(
                        data = user,
                        messageCode = AuthenticationMessageCode.SIGNUP_SUCCESS
                    )
                )
            }
            else {
                Pair(
                    HttpStatusCode.InternalServerError,
                    BaseResponse(
                        messageCode = AuthenticationMessageCode.SIGNUP_FAIL
                    )
                )
            }
        }
        else {
            Pair(
                HttpStatusCode.BadRequest,
                BaseResponse(
                    data = null,
                    messageCode = AuthenticationMessageCode.EMAIL_USED
                )
            )
        }
    }

    override suspend fun login(loginRequest: LoginRequest): Pair<HttpStatusCode,BaseResponse<LoginResponse?>> {
        val user = userDAO.getUserByEmail(loginRequest.email)
        return if (user != null) {
            val isPasswordCorrect = BCrypt.checkpw(loginRequest.password, user.password)
            return if (isPasswordCorrect) {
                val token = JWTUltis.generateToken(user)
                val refreshToken = JWTUltis.generateReToken(user)
                val loginResponse = LoginResponse(
                    token = token,
                    refreshToken = refreshToken,
                )
                Pair(
                    HttpStatusCode.OK,
                    BaseResponse(
                        data = loginResponse,
                        messageCode = BaseMessageCode.READ_SUCCESS
                    )
                )
            }
            else {
                Pair(
                    HttpStatusCode.BadRequest,
                    BaseResponse(
                        messageCode = AuthenticationMessageCode.PASSWORD_WRONG
                    )
                )
            }
        }
        else {
            Pair(
                HttpStatusCode.BadRequest,
                BaseResponse(
                    messageCode = AuthenticationMessageCode.EMAIL_NOT_FOUND
                )
            )
        }
    }
}