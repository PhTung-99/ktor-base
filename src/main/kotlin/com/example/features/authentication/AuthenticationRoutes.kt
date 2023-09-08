package com.example.features.authentication

import com.example.authentication.JWTUtils
import com.example.authentication.getToken
import com.example.data.models.BaseResponse
import com.example.features.authentication.constants.AuthenticationMessageCode
import com.example.features.authentication.models.requests.LoginRequest
import com.example.features.authentication.models.requests.LogoutRequest
import com.example.features.authentication.models.requests.RefreshRequest
import com.example.features.authentication.models.requests.SignupRequest
import com.example.features.authentication.repository.AuthenticationRepository
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*
import javax.lang.model.type.NullType

fun Route.authenticationRoute() {

    val authenticationRepository: AuthenticationRepository by inject()

    route("/authentication") {
        post("signup") {
            val multipart  = call.receiveMultipart()
            var email: String? = null
            var password: String? = null
            var name: String? = null

            //avatar
            var fileBytes: ByteArray? = null
            var originalFileName: String? = null

            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "email" -> email = part.value
                            "password" -> password = part.value
                            "name" -> name = part.value
                        }
                    }
                    is PartData.FileItem ->{
                        if (part.name == "avatar") {
                            fileBytes = part.streamProvider().readBytes()
                            originalFileName = part.originalFileName
                        }
                    }
                    else -> {}
                }
                part.dispose()
            }
            if (
                email.isNullOrBlank() ||
                password.isNullOrBlank() ||
                name.isNullOrBlank()
            ) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    BaseResponse<NullType>(
                        messageCode = AuthenticationMessageCode.INVALID_INFO
                    )
                )
            }
            else {
                val request = SignupRequest(
                    email = email!!,
                    password =password!!,
                    name = name!!,
                )
                val response = authenticationRepository.signup(request, fileBytes, originalFileName)
                call.respond(response.first, response.second)
            }
        }

        post("login") {
            val parameters = call.receiveParameters()
            if (
                parameters["email"].isNullOrEmpty() ||
                parameters["password"].isNullOrEmpty()
            ) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    BaseResponse<NullType>(
                        messageCode = AuthenticationMessageCode.INVALID_INFO
                    )
                )
            }
            else {
                val request = LoginRequest(
                    parameters["email"]!!,
                    parameters["password"]!!
                )
                val response = authenticationRepository.login(request)
                call.respond(response.first, response.second)
            }
        }

        post("refresh") {
            val request = call.receive<RefreshRequest>()
            val userId = JWTUtils.getClaimByToken(request.refreshToken, JWTUtils.USER_ID_KEY)
            val uuid = UUID.fromString(userId)
            val response = authenticationRepository.refreshToken(uuid, request.refreshToken)
            call.respond(response.first, response.second)
        }

        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            post("logout") {
                val token = call.getToken()
                val request = call.receive<LogoutRequest>()
                val response = authenticationRepository.logout(token!!, request.refreshToken)
                call.respond(response.first, response.second)
            }
        }
    }
}