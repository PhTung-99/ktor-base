package com.example.features.authentication

import com.example.features.authentication.models.LoginRequest
import com.example.features.authentication.models.SignupRequest
import com.example.features.authentication.repository.AuthenticationRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.authenticationRoute() {

    val authenticationRepository: AuthenticationRepository by inject()

    route("/authentication") {
        post("signup") {
            try {
                val request = call.receive<SignupRequest>()
                val response = authenticationRepository.signup(request)
                call.respond(HttpStatusCode.OK, response.response)
            }
            catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        post("login") {
            try {
                val request = call.receive<LoginRequest>()
                val response = authenticationRepository.login(request)
                call.respond(response.httpStatusCode, response.response)
            }
            catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
    }
}