package com.example.features.authentication.repository

import com.example.data.models.BaseMessageCode
import com.example.data.models.BaseResponse
import com.example.data.models.HttpResponse
import com.example.data.user.dao.UserDAO
import com.example.data.user.models.User
import com.example.features.authentication.constants.AuthenticationMessageCode
import com.example.features.authentication.models.LoginRequest
import com.example.features.authentication.models.SignupRequest
import io.ktor.http.*
import org.mindrot.jbcrypt.BCrypt

class AuthenticationRepositoryImpl(
    private val userDAO: UserDAO
): AuthenticationRepository {

    private suspend fun isEmailAvailable(email: String): Boolean {
        val count = userDAO.countEmailUsed(email)
        return count != 0L
    }

    override suspend fun signup(signupRequest: SignupRequest): BaseResponse<User?> {
        return if (isEmailAvailable(signupRequest.email)) {
            val hashedPassword = BCrypt.hashpw(signupRequest.password, BCrypt.gensalt())
            val user = userDAO.createUser(
                email = signupRequest.email,
                name = signupRequest.name,
                password = hashedPassword
            )
            return if (user != null) {
                BaseResponse(
                    httpStatusCode = HttpStatusCode.Created,
                    response = HttpResponse(
                        data = user,
                        messageCode = AuthenticationMessageCode.SIGNUP_SUCCESS
                    )
                )
            }
            else {
                BaseResponse(
                    httpStatusCode = HttpStatusCode.InternalServerError,
                    response = HttpResponse(
                        data = null,
                        messageCode = AuthenticationMessageCode.SIGNUP_FAIL
                    )
                )
            }
        }
        else {
            BaseResponse(
                httpStatusCode = HttpStatusCode.BadRequest,
                response = HttpResponse(
                    data = null,
                    messageCode = AuthenticationMessageCode.EMAIL_USED
                )
            )
        }
    }

    override suspend fun login(loginRequest: LoginRequest): BaseResponse<User?> {
        val user = userDAO.getUserByEmail(loginRequest.email)
        return if (user != null) {
            val isPasswordCorrect = BCrypt.checkpw(loginRequest.password, user.password)
            return if (isPasswordCorrect) {
                BaseResponse(
                    httpStatusCode = HttpStatusCode.OK,
                    response = HttpResponse(
                        data = user,
                        messageCode = BaseMessageCode.READ_SUCCESS
                    )
                )
            }
            else {
                BaseResponse(
                    httpStatusCode = HttpStatusCode.BadRequest,
                    response = HttpResponse(
                        data = null,
                        messageCode = AuthenticationMessageCode.PASSWORD_WRONG
                    )
                )
            }
        }
        else {
            BaseResponse(
                httpStatusCode = HttpStatusCode.BadRequest,
                response = HttpResponse(
                    data = null,
                    messageCode = AuthenticationMessageCode.EMAIL_NOT_FOUND
                )
            )
        }
    }
}