package com.example.authentication

import com.auth0.jwt.interfaces.Payload
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.util.UUID

class AppJWTPrincipal(payload: Payload) : Principal, JWTPayloadHolder(payload) {
    val userId: UUID get() {
        val idString = payload.getClaim(JWTUtils.USER_ID_KEY).asString()
        return UUID.fromString(idString)
    }
}