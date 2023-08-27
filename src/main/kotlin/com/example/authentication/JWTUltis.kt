package com.example.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.user.models.User
import com.example.property.AppProperties
import java.util.*

object JWTUltis {

    private val properties = AppProperties.jwtProperties

    fun getVerifier(): JWTVerifier {
        return JWT
            .require(Algorithm.HMAC256(properties.secret))
            .withAudience(properties.audience)
            .withIssuer(properties.issuer)
            .build()
    }


    fun generateToken(user: User): String {
        return JWT.create()
            .withAudience(properties.audience)
            .withIssuer(properties.issuer)
            .withClaim("userId", user.id.toString())
            .withExpiresAt(getExpiration())
            .sign(Algorithm.HMAC256(properties.secret))
    }

    fun generateReToken(user: User): String {
        return JWT.create()
            .withAudience(properties.audience)
            .withIssuer(properties.issuer)
            .withClaim("userId", user.id.toString())
            .withExpiresAt(getRefreshExpiration())
            .sign(Algorithm.HMAC256(properties.secret))
    }



    private const val validityInMs = 3600000 // 1 hour
    private const val validityRefreshInMs = 86_400_000  // 1 hour

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

    private fun getRefreshExpiration() = Date(System.currentTimeMillis() + validityRefreshInMs)
}