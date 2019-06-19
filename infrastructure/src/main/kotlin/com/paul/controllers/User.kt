package com.paul.controllers

import com.paul.entity.UserDataClass
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun Routing.users(){
    get("/users"){

        var newUser = call.receive<UserDataClass>()

        Database.connect(
            "jdbc:postgresql://localhost:5432/postgres_demo?user=strath&password=5trathm0re",
            driver="org.h2.Driver"
        )

        transaction {

        }

        call.respond(
            mapOf("OK" to true)
        )
    }

    post("/users"){
        call.respond(
            mapOf("OK" to true)
        )
    }
}