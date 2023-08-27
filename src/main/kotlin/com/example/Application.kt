package com.example

import com.example.authentication.configAuthentication
import com.example.data.database.DatabaseFactory
import com.example.di.configureKoin
import com.example.logging.configureLogging
import com.example.property.AppProperties
import com.example.routes.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    AppProperties.init(environment.config)
    configureLogging()
    configureKoin()
    configAuthentication()
    DatabaseFactory.init()
    configureRouting()

    install(ContentNegotiation) {
        json(
            Json {
                encodeDefaults = false
                prettyPrint = true
                isLenient = true
            }
        )
    }
}
