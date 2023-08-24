package com.example.data.database

import com.example.data.user.entity.UserEntity
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    var isConnected = false

    fun init(config: ApplicationConfig) {
        isConnected = try {
            val driverClassName = config.property("postgres.driver").getString()
            val jdbcURL = config.property("postgres.url").getString()
            val user = config.property("postgres.user").getString()
            val password = config.property("postgres.password").getString()
            val database = Database.connect(jdbcURL, driverClassName, user, password)
            database.connector() // Attempt to connect
            transaction(database) {
                SchemaUtils.create(UserEntity)
            }
            true
        } catch (e: Exception) {
            false
        }
    }


    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}