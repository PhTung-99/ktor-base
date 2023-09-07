package com.example.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.features.user.models.User
import com.example.property.AppProperties
import io.ktor.server.auth.jwt.*
import java.util.*

object JWTUtils {

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

    fun isTokenValid(token: String): Boolean {
        return try {
            verifier.verify(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getClaimByToken(token: String, key: String): String? {
        return verifier.verify(token).claims[key]?.asString()
    }



    val validityInMs: Long = properties.expireMinutes * 60L * 1000L // 1 hour
    val validityRefreshInMs: Long = properties.expireRefreshMinutes * 60L * 1000L  //  30 days

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

    private fun getRefreshExpiration() = Date(System.currentTimeMillis() + validityRefreshInMs)
}
