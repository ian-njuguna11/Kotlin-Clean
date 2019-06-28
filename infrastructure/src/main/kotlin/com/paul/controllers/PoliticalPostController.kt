package com.paul.controllers

import com.paul.entity.PoliticalPostDataClass
import com.paul.politicalPostRepo
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import org.jetbrains.exposed.exceptions.ExposedSQLException

fun Routing.politicalPost(){

    get("/political-posts"){
        call.respond(politicalPostRepo.findAllPoliticalPosts())
    }

    post("/political-posts"){

        val newPoliticalPost = call.receive<PoliticalPostDataClass>()

        try {
            politicalPostRepo.create(newPoliticalPost)
        } catch(e: ExposedSQLException){
            call.respond(HttpStatusCode.NotAcceptable , mapOf("error" to "political post with that name already exists"))
            return@post
        }

        call.respond(HttpStatusCode.Created, mapOf("OK" to true))
    }

}