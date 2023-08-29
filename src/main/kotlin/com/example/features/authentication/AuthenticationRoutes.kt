package com.example.features.authentication

import com.example.authentication.JWTUtils
import com.example.features.authentication.models.requests.LoginRequest
import com.example.features.authentication.models.requests.RefreshRequest
import com.example.features.authentication.models.requests.SignupRequest
import com.example.features.authentication.repository.AuthenticationRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.authenticationRoute() {

    val authenticationRepository: AuthenticationRepository by inject()

    route("/authentication") {
        post("signup") {
            val request = call.receive<SignupRequest>()
            val response = authenticationRepository.signup(request)
            call.respond(response.first, mapOf("data" to response.second))
        }

        post("login") {
            val request = call.receive<LoginRequest>()
            val response = authenticationRepository.login(request)
            call.respond(response.first, response.second)
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