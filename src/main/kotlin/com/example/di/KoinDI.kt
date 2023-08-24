package com.example.di

import com.example.features.user.userModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(userModule)
    }
}