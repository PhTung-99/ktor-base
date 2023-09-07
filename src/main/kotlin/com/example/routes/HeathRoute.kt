package com.example.routes

import com.example.data.database.DatabaseFactory
import com.example.data.models.HealthCheck
import com.example.data.redis.RedisClient
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.healthRoute() {
    route("/health") {
        get {
            call.respond(HttpStatusCode.OK, HealthCheck(
                database = if(DatabaseFactory.isConnected) "connected" else "unconnected",
                redis = if(RedisClient.isConnected) "connected" else "unconnected",
            ))
        }
    }
}