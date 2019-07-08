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
import io.ktor.routing.get
import io.ktor.routing.post

fun Routing.votes(){
    /*
    *
     */
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

        call.respond(HttpStatusCode.Created, mapOf("message" to "vote successfully inserted"))
    }

    /*
    * get vote tally for a single political post
    */
    get("/post-votes-tally/{post_id}"){

        val postId: Long?
        try{
            postId = call.parameters["post_id"]!!.toLong()
        } catch(e: NumberFormatException){
            call.respond(HttpStatusCode.NotAcceptable, mapOf("error" to "post_id should be a number"))
            return@get
        }

        val voteTally: HashMap<String, String> ?
        try{
            voteTally = voteRepo.findSinglePostResults(postId)
        } catch(e: PoliticalPostDoesNotExistException){
            call.respond(HttpStatusCode.NotFound, mapOf("error" to e.message))
            return@get
        }

        call.respond(voteTally)

    }


    /*
    * get vote tally for all the political posts
    */
    get("/votes-tally"){
        val voteTally = voteRepo.findAllPostsResults()
        call.respond(voteTally)
    }


}