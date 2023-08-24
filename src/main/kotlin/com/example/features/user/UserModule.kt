package com.example.features.user

import com.example.features.user.repository.UserRepository
import com.example.features.user.repository.UserRepositoryImpl
import org.koin.dsl.module

val userModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}