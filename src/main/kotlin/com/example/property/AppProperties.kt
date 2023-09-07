package com.example.property

import io.ktor.server.config.*

object AppProperties {

    lateinit var env: String

    lateinit var postgresProperties: PostgresProperties
    lateinit var jwtProperties: JWTProperties
    lateinit var redisProperty: RedisProperty

    fun init(applicationConfig: ApplicationConfig) {
        val ktorEnv = applicationConfig.propertyOrNull("ktor.environment")?.getString()
        if (!ktorEnv.isNullOrBlank()) {
            env = when (ktorEnv) {
                "local" -> "local"
                "dev" -> "dev"
                else -> "local"
            }
        }
        initPostgresProperties(applicationConfig)
        initJWTProperties(applicationConfig)
        initRedisProperties(applicationConfig)
    }

    private fun initPostgresProperties(config: ApplicationConfig) {
        val path = "$env.postgres"
        postgresProperties = PostgresProperties(
            driver = config.property("$path.driver").getString(),
            url = config.property("$path.url").getString(),
            user = config.property("$path.user").getString(),
            password = config.property("$path.password").getString(),
        )
    }

    private fun initJWTProperties(config: ApplicationConfig) {
        val path = "$env.jwt"
        jwtProperties = JWTProperties(
            secret = config.property("$path.secret").getString(),
            refreshSecret = config.property("$path.refreshSecret").getString(),
            issuer = config.property("$path.issuer").getString(),
            audience = config.property("$path.audience").getString(),
            realm = config.property("$path.realm").getString(),
            expireMinutes = config.property("$path.expireMinutes").getString().toLong(),
            expireRefreshMinutes = config.property("$path.expireRefreshMinutes").getString().toLong(),
        )
    }

    private fun initRedisProperties(config: ApplicationConfig) {
        val path = "$env.redis"
        redisProperty = RedisProperty(
            host = config.property("$path.host").getString(),
            port = config.property("$path.port").getString().toInt()
        )
    }
}
