package com.example.features.user

import com.example.features.user.models.requests.SignupRequest
import com.example.features.user.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {

    val userRepository: UserRepository by inject()

    route("/user") {
        post("signup") {
            val request = call.receive<SignupRequest>()
            try {
                val user = userRepository.signUp(request)
                call.respond(HttpStatusCode.Created, mapOf("user" to user))
            }
            catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, mapOf("user" to "error"))
            }
        }
    }
}