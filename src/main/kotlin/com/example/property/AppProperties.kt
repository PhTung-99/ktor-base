package com.example.property

import io.ktor.server.config.*

object AppProperties {

    lateinit var postgresProperties: PostgresProperties
    lateinit var jwtProperties: JWTProperties
    lateinit var redisProperty: RedisProperty

    fun init(applicationConfig: ApplicationConfig) {
        initPostgresProperties(applicationConfig)
        initJWTProperties(applicationConfig)
        initRedisProperties(applicationConfig)
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
            expireMinutes = config.property("jwt.expireMinutes").getString().toInt(),
            expireRefreshMinutes = config.property("jwt.expireRefreshMinutes").getString().toInt(),
        )
    }

    private fun initRedisProperties(config: ApplicationConfig) {
        redisProperty = RedisProperty(
            host = config.property("redis.host").getString(),
            port = config.property("redis.port").getString().toInt()
        )
    }


}
