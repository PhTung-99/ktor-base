package com.example.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.features.user.models.User
import com.example.property.AppProperties
import io.ktor.server.auth.jwt.*
import java.util.*

object JWTUltis {

    const val USER_ID_KEY = "userId"

    private val properties = AppProperties.jwtProperties

    val verifier: JWTVerifier by lazy {
        JWT
            .require(Algorithm.HMAC256(properties.secret))
            .withAudience(properties.audience)
            .withIssuer(properties.issuer)
            .build()
    }

    fun generateToken(user: User): String {
        return JWT.create()
            .withAudience(properties.audience)
            .withIssuer(properties.issuer)
            .withClaim(USER_ID_KEY, user.id.toString())
            .withExpiresAt(getExpiration())
            .sign(Algorithm.HMAC256(properties.secret))
    }

    fun generateReToken(user: User): String {
        return JWT.create()
            .withAudience(properties.audience)
            .withIssuer(properties.issuer)
            .withClaim(USER_ID_KEY, user.id.toString())
            .withExpiresAt(getRefreshExpiration())
            .sign(Algorithm.HMAC256(properties.secret))
    }

    fun getClaim(principal: JWTPrincipal, key: String): String {
        return principal.payload.getClaim(key).asString()
    }



    private const val validityInMs = 3600000 // 1 hour
    private const val validityRefreshInMs = 86_400_000  // 1 hour

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

    private fun getRefreshExpiration() = Date(System.currentTimeMillis() + validityRefreshInMs)
}
