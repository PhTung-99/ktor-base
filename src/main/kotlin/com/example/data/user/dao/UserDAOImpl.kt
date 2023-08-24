package com.example.data.user.dao

import com.example.data.database.DatabaseFactory.dbQuery
import com.example.data.user.entity.UserEntity
import com.example.data.user.mapper.resultRowToUser
import com.example.data.user.models.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDAOImpl(): UserDAO {

    override suspend fun countEmailUsed(email: String): Long {
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

    override suspend fun getUserByEmail(email: String): User? = dbQuery {
        val user = UserEntity.select {
            UserEntity.email eq email
        }
        return@dbQuery user.singleOrNull()?.let(::resultRowToUser)
    }
}