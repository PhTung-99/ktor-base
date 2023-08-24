package com.example.features.authentication

import com.example.features.authentication.repository.AuthenticationRepository
import com.example.features.authentication.repository.AuthenticationRepositoryImpl
import org.koin.dsl.module

val authenticationModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
}