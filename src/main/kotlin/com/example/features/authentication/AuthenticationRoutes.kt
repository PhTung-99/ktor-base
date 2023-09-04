package com.example.features.authentication

import com.example.authentication.JWTUtils
import com.example.data.models.BaseResponse
import com.example.features.authentication.constants.AuthenticationMessageCode
import com.example.features.authentication.models.requests.LoginRequest
import com.example.features.authentication.models.requests.RefreshRequest
import com.example.features.authentication.models.requests.SignupRequest
import com.example.features.authentication.repository.AuthenticationRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*
import javax.lang.model.type.NullType

fun Route.authenticationRoute() {

    val authenticationRepository: AuthenticationRepository by inject()

    route("/authentication") {
        post("signup") {
            val parameters = call.receiveParameters()
            if (
                parameters["email"].isNullOrBlank() ||
                parameters["password"].isNullOrBlank() ||
                parameters["name"].isNullOrBlank()
            ) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    BaseResponse<NullType>(
                        messageCode = AuthenticationMessageCode.INVALID_INFO
                    )
                )
            }
            else {
                val request = SignupRequest(
                    email = parameters["email"]!!,
                    password = parameters["password"]!!,
                    name = parameters["name"]!!,
                )
                val response = authenticationRepository.signup(request)
                call.respond(response.first, response.second)
            }
        }

        post("login") {
            val parameters = call.receiveParameters()
            if (
                parameters["email"].isNullOrEmpty() ||
                parameters["password"].isNullOrEmpty()
            ) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    BaseResponse<NullType>(
                        messageCode = AuthenticationMessageCode.INVALID_INFO
                    )
                )
            }
            else {
                val request = LoginRequest(
                    parameters["email"]!!,
                    parameters["password"]!!
                )
                val response = authenticationRepository.login(request)
                call.respond(response.first, response.second)
            }
        }

        authenticate("auth-jwt") {
            post("refresh") {
                val principal = call.principal<JWTPrincipal>()
                val request = call.receive<RefreshRequest>()
                val userId = JWTUtils.getClaim(principal!!, JWTUtils.USER_ID_KEY)
                val uuid = UUID.fromString(userId)
                val response = authenticationRepository.refreshToken(uuid, request.refreshToken)
                call.respond(response.first, response.second)
            }
        }
    }
}