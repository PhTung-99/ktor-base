package com.example.data

import com.example.data.user.dao.UserDAO
import com.example.data.user.dao.UserDAOImpl
import org.koin.dsl.module

val dataModule = module {
    single<UserDAO> { UserDAOImpl() }
}