package com.example.di

import com.example.data.dataModule
import com.example.features.authentication.authenticationModule
import com.example.features.user.userModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(dataModule)
        modules(userModule)
        modules(authenticationModule)

    }
}