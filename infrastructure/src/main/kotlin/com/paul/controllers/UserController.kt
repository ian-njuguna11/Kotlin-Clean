package com.paul.controllers

import com.paul.entity.UserDataClass
import com.paul.port.ValidationException
import com.paul.ports.UserPorts
import com.paul.userRepo
import com.paul.models.User as UserModel
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.lang.NumberFormatException

fun Routing.users() {

    get("/users") {
        val response = userRepo.findAllUsers()
        call.respond(response)
    }

    post("/users") {
        val newUser = call.receive<UserDataClass>()

        try {
            userRepo.create(newUser)
        } catch (e: ExposedSQLException) {
            call.respond(mapOf("error" to "email already used"))
            return@post
        }

        call.respond(
            mapOf("OK" to true)
        )

    }

    get("/users/{user_id}") {

        val id: Long?

        try {
            id = call.parameters["user_id"]!!.toLong()
        } catch (e: NumberFormatException) { // checks when 'user_id' is not a number
            call.respond(HttpStatusCode.NotAcceptable, mapOf("error" to "user_id must be a number"))
            return@get
        }

        val user = userRepo.findUserById(id)
        if (user.isEmpty()) {
            call.respond(HttpStatusCode.NotFound, mapOf("error" to "user not found"))
            return@get
        }

        call.respond(user)
    }

    post("/users/login") {
        val loginUser = call.receive<UserDataClass>()

        val user: Map<String, String>

        try {
            user = userRepo.findUserByEmail(loginUser.email, UserPorts.hashPassword( loginUser.password))!!
        } catch (e: ValidationException) {
            call.respond(HttpStatusCode.NotAcceptable, mapOf("error" to e.message))
            return@post
        }

        if (user.isEmpty()) {
            call.respond(HttpStatusCode.NotFound, mapOf("error" to "user not found"))
            return@post
        }
        call.respond(user)
    }
}
