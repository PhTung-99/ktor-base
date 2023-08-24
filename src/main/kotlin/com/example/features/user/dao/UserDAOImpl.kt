package com.example.features.user.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.features.user.entity.UserEntity
import com.example.features.user.mapper.resultRowToUser
import com.example.features.user.models.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDAOImpl(): UserDAO {

    override suspend fun getEmailUsed(email: String): Long {
        val count = UserEntity.select {
            UserEntity.email eq email
        }.count()
        return count
    }

    override suspend fun createUser(email: String, name: String, password: String): User? = dbQuery {
        val createStatement = UserEntity.insert {
            it[UserEntity.name] = name
            it[UserEntity.email] = email
            it[UserEntity.password] = password
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }
}