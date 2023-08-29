package com.example.authentication

import com.example.constants.Expressions
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*


fun Application.configAuthentication() {

    install(Authentication) {
        jwt("auth-jwt") {
            verifier(JWTUltis.verifier)
            validate { jwtCredential ->
                val userId = jwtCredential.payload.getClaim("userId").asString()
                if (userId != "") {
                    if (Expressions.UUID_REGEX.matcher(userId).matches()) {
                        JWTPrincipal(jwtCredential.payload)
                    }
                    else {
                        null
                    }
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
