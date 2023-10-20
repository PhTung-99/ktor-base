package com.example.features.errorutils

import com.example.data.models.BaseResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.serverErrorRoutes() {
    route("/serverError") {
        get("500") {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse<Nothing>(messageCode = "500"))
        }

        get("501") {
            call.respond(HttpStatusCode.NotImplemented, BaseResponse<Nothing>(messageCode = "501"))
        }

        get("502") {
            call.respond(HttpStatusCode.BadGateway, BaseResponse<Nothing>(messageCode = "502"))
        }

        get("503") {
            call.respond(HttpStatusCode.ServiceUnavailable, BaseResponse<Nothing>(messageCode = "503"))
        }
    }
}