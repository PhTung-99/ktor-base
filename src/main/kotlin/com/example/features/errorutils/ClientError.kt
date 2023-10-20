package com.example.features.errorutils

import com.example.data.models.BaseResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.clientErrorRoutes() {

    route("/clientError") {
        get("400") {
            call.respond(HttpStatusCode.BadRequest, BaseResponse<Nothing>(messageCode = "400"))
        }

        get("401") {
            call.respond(HttpStatusCode.Unauthorized, BaseResponse<Nothing>(messageCode = "401"))
        }

        get("403") {
            call.respond(HttpStatusCode.Forbidden, BaseResponse<Nothing>(messageCode = "403"))
        }

        get("404") {
            call.respond(HttpStatusCode.NotFound)
        }

        get ("405") {
            call.respond(HttpStatusCode.MethodNotAllowed)
        }
    }
}