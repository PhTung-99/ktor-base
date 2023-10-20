package com.example.features.errorutils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.serverErrorRoutes() {
    route("/serverError") {
        get("500") {
            call.respond(HttpStatusCode.InternalServerError)
        }

        get("501") {
            call.respond(HttpStatusCode.NotImplemented)
        }

        get("502") {
            call.respond(HttpStatusCode.BadGateway)
        }

        get("503") {
            call.respond(HttpStatusCode.ServiceUnavailable)
        }
    }
}