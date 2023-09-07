package com.example.routes

import com.example.features.authentication.authenticationRoute
import com.example.features.user.userRoutes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Paths

fun Application.configureRouting() {
    val pathImage = "${Paths.get("").toAbsolutePath().toString()}/static-content/images/"
//    jwtInterceptor()
    routing {
        staticFiles("/static-content/images", File(pathImage))
        userRoutes()
        healthRoute()
        authenticationRoute()
    }
}

//fun Application.jwtInterceptor() {
//    intercept(ApplicationCallPipeline.Call) {
//
//        if (call.authentication.principal<JWTPrincipal>() == null) {
//            // JWT verification failed or token missing
//            call.respond(HttpStatusCode.Unauthorized, "Missing or invalid token")
//            return@intercept finish()
//        }
//
//        if (RedisClient.jedis.exists(token)) {
//            call.respond(
//                HttpStatusCode.Unauthorized,
//                mapOf("messageCode" to AuthenticationMessageCode.INVALID_TOKEN),
//            )
//            return@intercept finish()
//        }
//
//        proceed()
//    }
//}
