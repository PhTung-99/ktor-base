package com.example.routes

import com.example.features.authentication.authenticationRoute
import com.example.features.errorutils.clientErrorRoutes
import com.example.features.errorutils.serverErrorRoutes
import com.example.features.user.userRoutes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Paths

fun Application.configureRouting() {
    val pathImage = "${Paths.get("").toAbsolutePath()}/static-content/images/"
    routing {
        staticFiles("/static-content/images", File(pathImage))
        clientErrorRoutes()
        serverErrorRoutes()
        userRoutes()
        healthRoute()
        authenticationRoute()
    }
}
