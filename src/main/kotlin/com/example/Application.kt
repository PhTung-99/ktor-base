package com.example

import com.example.authentication.configAuthentication
import com.example.data.database.DatabaseFactory
import com.example.di.configureKoin
import com.example.logging.configureLogging
import com.example.property.AppProperties
import com.example.routes.*
import configureSerializable
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    AppProperties.init(environment.config)
    DatabaseFactory.init()
    configureSerializable()
    configureLogging()
    configureKoin()
    configAuthentication()
    configureRouting()
}
