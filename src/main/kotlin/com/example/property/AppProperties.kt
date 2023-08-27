package com.example.property

import io.ktor.server.config.*

object AppProperties {

    lateinit var postgresProperties: PostgresProperties
    lateinit var jwtProperties: JWTProperties

    fun init(applicationConfig: ApplicationConfig) {
        initPostgresProperties(applicationConfig)
        initJWTProperties(applicationConfig)
    }

    private fun initPostgresProperties(config: ApplicationConfig) {
        postgresProperties = PostgresProperties(
            driver = config.property("postgres.driver").getString(),
            url = config.property("postgres.url").getString(),
            user = config.property("postgres.user").getString(),
            password = config.property("postgres.password").getString(),
        )
    }

    private fun initJWTProperties(config: ApplicationConfig) {
        jwtProperties = JWTProperties(
            secret = config.property("jwt.secret").getString(),
            refreshSecret = config.property("jwt.refreshSecret").getString(),
            issuer = config.property("jwt.issuer").getString(),
            audience = config.property("jwt.audience").getString(),
            realm = config.property("jwt.realm").getString(),
        )
    }


}
