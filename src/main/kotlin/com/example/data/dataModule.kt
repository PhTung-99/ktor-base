package com.example.data

import com.example.data.features.user.dao.UserDAO
import com.example.data.features.user.dao.UserDAOImpl
import com.example.data.features.user.dao.UserTokenDAO
import com.example.data.features.user.dao.UserTokenDAOImpl
import org.koin.dsl.module

val dataModule = module {
    single<UserDAO> { UserDAOImpl() }
    single<UserTokenDAO> { UserTokenDAOImpl() }
}