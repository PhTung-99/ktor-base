package com.example.property

data class JWTProperties (
    val secret: String,
    val refreshSecret: String,
    val issuer: String,
    val audience: String,
    val realm: String
)
