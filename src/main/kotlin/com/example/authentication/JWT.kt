package com.example.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.routes.healthRoute
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.bearer
import io.ktor.server.auth.jwt.*
import io.ktor.server.routing.*

fun Application.configAuthentication() {
    val myRealm = environment.config.property("jwt.realm").getString()
    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()
    install(Authentication) {
        jwt("auth-jwt") {
            realm = myRealm
            verifier(
                JWT
                .require(Algorithm.HMAC256(secret))
                .withAudience(audience)
                .withIssuer(issuer)
                .build())

            validate { credential ->
                if (credential.payload.getClaim("username").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }

//            challenge { defaultScheme, realm ->
//                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
//            }
        }
    }
}