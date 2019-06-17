package com.weqe.controllers

import com.weqe.repos.CreateUser
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post

data class UserStructure(
    val email: String,
    val name: String,
    val password: String
)

fun Routing.users(){
    post("/"){
        val newUser = call.receive<UserStructure>()

        val createUser = CreateUser(
            newUser.name, newUser.email, newUser.password
        )
        createUser.execute()

        call.respond(
            mapOf("OK" to true)
        )
    }
}