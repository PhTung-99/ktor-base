package com.example.routes

import com.example.data.database.DatabaseFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.nio.file.Paths


fun Route.healthRoute() {
    route("/health") {
        get {
            if (DatabaseFactory.isConnected) {
                call.respond(HttpStatusCode.OK, mapOf("database" to "connected", "path" to Paths.get("").toAbsolutePath().toString()))
            } else {
                call.respond(HttpStatusCode.InternalServerError, mapOf("health" to "unconnected", "path" to Paths.get("").toAbsolutePath().toString()))
            }
        }
    }
}