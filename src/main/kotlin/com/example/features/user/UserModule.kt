package com.example.features.user

import com.example.features.user.dao.UserDAOImpl
import com.example.features.user.dao.UserDAO
import com.example.features.user.repository.UserRepository
import com.example.features.user.repository.UserRepositoryImpl
import org.koin.dsl.module

val userModule = module {
    single<UserDAO> { UserDAOImpl() } // get() Will resolve NotesRepository
    single<UserRepository> { UserRepositoryImpl(get()) }
}