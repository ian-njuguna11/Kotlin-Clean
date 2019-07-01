package com.paul.controllers

import com.paul.entity.VoteDataClass
import com.paul.port.PoliticalPostDoesNotExistException
import com.paul.port.PoliticianDoesNotExistException
import com.paul.port.UserDoesNotExistException
import com.paul.voteRepo
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post

fun Routing.votes(){

    post("/votes"){

        val newVote = call.receive<VoteDataClass>()

        try{
            voteRepo.create(newVote)
        } catch(e: Exception){
            when (e){
                is UserDoesNotExistException, is PoliticianDoesNotExistException, is PoliticalPostDoesNotExistException  -> {
                    call.respond(HttpStatusCode.NotFound, mapOf("error" to e.message))
                    return@post
                }
            }
        }

        call.respond(mapOf("message" to "vote successfully inserted"))
    }

}