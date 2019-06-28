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
import java.lang.NumberFormatException

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

    get("/political-posts/id/{post_id}"){

        val id: Long?

        try{
            id = call.parameters["post_id"]!!.toLong()
        } catch(e: NumberFormatException){
            call.respond(HttpStatusCode.NotAcceptable, mapOf("error" to "post_id must be a number"))
            return@get
        }

        val post = politicalPostRepo.findRepoById(id)

        if (post.isEmpty())
            call.respond(HttpStatusCode.NotFound, mapOf("error" to "political post could not be found"))
        else
            call.respond(post)

    }

    get("/political-posts/name/{post_name}"){
        val name: String = call.parameters["post_name"].toString()
        val post = politicalPostRepo.findPostByName(name)
        if (post.isEmpty())
            call.respond(HttpStatusCode.NotFound, mapOf("error" to "political post could not be found"))
        else
            call.respond(post)
    }

}