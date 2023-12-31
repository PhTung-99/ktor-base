package com.example

import com.example.authentication.configAuthentication
import com.example.data.database.DatabaseFactory
import com.example.data.redis.RedisClient
import com.example.data.validation.validationConfig
import com.example.di.configureKoin
import com.example.logging.configureLogging
import com.example.property.AppProperties
import com.example.routes.*
import com.example.utils.fileInit
import com.example.plugin.serializable.configureSerializable
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    AppProperties.init(environment.config)
    fileInit()
    DatabaseFactory.init()
    RedisClient.init()
    validationConfig()
    configureSerializable()
    configureLogging()
    configureKoin()
    configAuthentication()
    configureRouting()
}
