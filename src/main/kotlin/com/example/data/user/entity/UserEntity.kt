package com.example.data.user.entity

import org.jetbrains.exposed.sql.Table

object UserEntity : Table("ktor-user") {
    val id = long("id").autoIncrement()
    val name = varchar("Name", 50)
    val email = varchar("Email", 50).uniqueIndex()
    val password = varchar("Password", 100)
    override val primaryKey = PrimaryKey(id)
}