package com.example.authentication

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configAuthentication() {

    install(Authentication) {
        jwt("auth-jwt") {
            verifier(JWTUltis.getVerifier())
            validate { jwtCredential ->
                if (jwtCredential.payload.getClaim("userId").asString() != "") {
                    JWTPrincipal(jwtCredential.payload)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}
