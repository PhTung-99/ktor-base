package com.example.data.database

import com.example.data.user.entity.UserEntity
import com.example.data.user.entity.UserTokenEntity
import com.example.property.AppProperties
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    var isConnected = false

    fun init() {
        isConnected = try {
            val driverClassName = AppProperties.postgresProperties.driver
            val jdbcURL = AppProperties.postgresProperties.url
            val user = AppProperties.postgresProperties.user
            val password = AppProperties.postgresProperties.password
            val database = Database.connect(jdbcURL, driverClassName, user, password)
            database.connector() // Attempt to connect
            transaction(database) {
                SchemaUtils.create(UserEntity)
                SchemaUtils.create(UserTokenEntity)
            }
            true
        } catch (e: Exception) {
            println(e.toString())
            false
        }
    }


    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}