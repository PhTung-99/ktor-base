package com.example.features.user

import com.example.authentication.AppJWTPrincipal
import com.example.authentication.JWTUtils
import com.example.features.user.repository.UserRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.userRoutes() {

    val userRepository: UserRepository by inject()

    route("/user") {

        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get("info") {
                val principal = call.principal<AppJWTPrincipal>()
                val response = userRepository.getUserInfo(principal!!.userId)
                call.respond(response.first, response.second)
            }
        }
    }
}