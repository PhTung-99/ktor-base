package com.example.routes

import com.example.features.user.userRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRoutes()
        healthRoute()
    }
}
