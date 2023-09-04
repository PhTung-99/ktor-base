package com.example.data.validation

import com.example.data.models.BaseResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import javax.lang.model.type.NullType

fun Application.validationConfig() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, BaseResponse<NullType>(
                messageCode = cause.toString()
            ))
        }
    }
}
