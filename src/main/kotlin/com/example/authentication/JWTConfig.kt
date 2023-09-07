package com.example.authentication

import com.example.constants.Expressions
import com.example.features.authentication.constants.AuthenticationMessageCode
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*


fun Application.configAuthentication() {

    install(Authentication) {
        jwt("auth-jwt") {
            verifier {
                httpAuthHeader -> JWTUtils.baseVerifier(httpAuthHeader)
            }
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
                call.respond(HttpStatusCode.Unauthorized, mapOf(
                    "messageCode" to AuthenticationMessageCode.INVALID_TOKEN)
                )
            }
        }
    }
}
