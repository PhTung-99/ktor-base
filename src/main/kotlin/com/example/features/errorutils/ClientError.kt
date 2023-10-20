package com.example.features.errorutils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.clientErrorRoutes() {

    route("/clientError") {
        get("400") {
            call.respond(HttpStatusCode.BadRequest)
        }

        get("401") {
            call.respond(HttpStatusCode.Unauthorized)
        }

        get("403") {
            call.respond(HttpStatusCode.Forbidden)
        }

        get("404") {
            call.respond(HttpStatusCode.NotFound)
        }

        get ("405") {
            call.respond(HttpStatusCode.MethodNotAllowed)
        }
    }
}