package com.example.data.features.user.mapper

import com.example.data.features.user.entity.UserEntity
import com.example.data.features.user.entity.UserTokenEntity
import com.example.data.features.user.models.User
import com.example.data.features.user.models.UserToken
import org.jetbrains.exposed.sql.ResultRow


fun resultRowToUserWithPassword(row: ResultRow) = User(
    id = row[UserEntity.id].value,
    name = row[UserEntity.name],
    email = row[UserEntity.email],
    password = row[UserEntity.password],
    createAtUTC = row[UserEntity.createAtUTC],
    isDeleted = row[UserEntity.isDeleted],
)

fun resultRowToUser(row: ResultRow) = User(
    id = row[UserEntity.id].value,
    name = row[UserEntity.name],
    email = row[UserEntity.email],
    createAtUTC = row[UserEntity.createAtUTC],
    isDeleted = row[UserEntity.isDeleted],
)

fun userTokenEntityToModel(row: ResultRow) = UserToken(
    id = row[UserTokenEntity.id].value,
    refreshToken = row[UserTokenEntity.refreshToken],
    userId =  row[UserTokenEntity.userId].value,
    createAtUTC = row[UserTokenEntity.createAtUTC],
    isDeleted = row[UserTokenEntity.isDeleted],
)