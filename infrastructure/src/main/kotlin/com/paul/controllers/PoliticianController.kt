package com.paul.controllers

import com.paul.entity.PoliticianDataClass
import com.paul.politicianRepo
import com.paul.port.PoliticianDoesNotExistException
import io.ktor.application.call
import io.ktor.http.HttpStatusCode

import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import java.lang.NumberFormatException


fun Routing.politician() {

    post("/politicians"){

        val politicianInfo = call.receive<PoliticianDataClass>()
        politicianRepo.create(politicianInfo)
        call.respond(HttpStatusCode.Created, mapOf("OK" to true))

    }

    get("/politicians/id/{politician_id}"){
        val id: Long?

        try{
            id = call.parameters["politician_id"]!!.toLong()
        } catch(e: NumberFormatException){
            call.respond(HttpStatusCode.NotAcceptable, mapOf("error" to "politician_id has to be a number"))
            return@get
        }

        val politician : HashMap<String, String> ?
        try {
            politician = politicianRepo.findPoliticianById(id)
        } catch(e: PoliticianDoesNotExistException){
            call.respond(HttpStatusCode.NotFound, mapOf("error" to e.message))
            return@get
        }

        call.respond(politician)

    }

}