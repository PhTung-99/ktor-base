package com.example

import com.example.database.DatabaseFactory
import com.example.di.configureKoin
import com.example.logging.configureLogging
import com.example.routes.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureLogging()
    configureKoin()
    DatabaseFactory.init(environment.config)
    configureRouting()

    install(ContentNegotiation) {
        json()
    }
}
