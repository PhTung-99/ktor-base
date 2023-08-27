package com.example.features.user

import com.example.features.user.repository.UserRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*


fun Route.userRoutes() {

    val userRepository: UserRepository by inject()

    route("/user") {

        authenticate("auth-jwt") {
            get("info") {
                val principal = call.principal<JWTPrincipal>()
                val username = principal!!.payload.getClaim("userId").asString()
                val response = userRepository.getUserInfo(UUID.fromString(username))
                call.respond(response.first, response.second)
            }
        }

    }
}