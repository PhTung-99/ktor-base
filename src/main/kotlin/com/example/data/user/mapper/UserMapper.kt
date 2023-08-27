package com.example.data.user.mapper

import com.example.data.user.entity.UserEntity
import com.example.data.user.models.User
import org.jetbrains.exposed.sql.ResultRow


fun resultRowToUserWithPassword(row: ResultRow) = User(
    id = row[UserEntity.id].value,
    name = row[UserEntity.name],
    email = row[UserEntity.email],
    password = row[UserEntity.password],
)

fun resultRowToUser(row: ResultRow) = User(
    id = row[UserEntity.id].value,
    name = row[UserEntity.name],
    email = row[UserEntity.email],
)