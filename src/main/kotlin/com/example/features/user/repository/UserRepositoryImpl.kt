package com.example.features.user.repository

import com.example.features.user.dao.UserDAO
import com.example.features.user.models.User
import com.example.features.user.models.requests.SignupRequest
import org.mindrot.jbcrypt.BCrypt

class UserRepositoryImpl(
    private val userDAO: UserDAO
): UserRepository {

    private suspend fun isEmailAvailable(email: String): Boolean {
        val count = userDAO.getEmailUsed(email)
        return count != 0L
    }

    override suspend fun signUp(signupRequest: SignupRequest): User? {
        if (isEmailAvailable(signupRequest.email)) {
            val hashedPassword = BCrypt.hashpw(signupRequest.password, BCrypt.gensalt())
            return userDAO.createUser(
                email = signupRequest.email,
                name = signupRequest.name,
                password = hashedPassword
            )
        }
        return null
    }
}