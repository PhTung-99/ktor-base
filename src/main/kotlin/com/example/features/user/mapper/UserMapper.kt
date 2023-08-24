package com.example.features.user.mapper

import com.example.features.user.entity.UserEntity
import com.example.features.user.models.User
import org.jetbrains.exposed.sql.ResultRow

fun resultRowToUser(row: ResultRow) = User(
    id = row[UserEntity.id],
    name = row[UserEntity.name],
    email = row[UserEntity.email],
)