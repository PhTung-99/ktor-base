package com.example.features.user.entity

import org.jetbrains.exposed.sql.Table

object UserEntity : Table("user") {
    val id = long("id").autoIncrement()
    val name = varchar("Name", 50)
    val email = varchar("Email", 50).uniqueIndex()
    val password = varchar("Password",20)
    override val primaryKey = PrimaryKey(id)
}