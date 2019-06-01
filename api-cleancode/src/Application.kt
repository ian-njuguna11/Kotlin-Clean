package com.example

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import com.fasterxml.jackson.databind.*
import com.paul.CreateUser
import com.paul.entity.UserDataClass
import io.ktor.jackson.*
import io.ktor.features.*

var users = mutableListOf(
    UserDataClass("paul", "paul@paul.com", "password_123", 30),
    UserDataClass("abc", "abc@abc.com", "password_123", 20)
)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    install (ContentNegotiation){
        jackson {

        }
    }

    routing {

        route("/login"){

            // login route
            post(){
                val login_user = call.receive<UserDataClass>()


            }

        }

        route("/") {

            // SIGN UP ROUTE
            get() {
                call.respond(mapOf("users" to users.toList()))
            }

            post() {

                val posted_user = call.receive<UserDataClass>()

                val createUserObj = CreateUser(posted_user)
                val createResponse = createUserObj.addUser()

                if (createResponse != "success"){
                    call.respond(mapOf("ERROR" to createResponse))
                    return@post
                }

                call.respond(mapOf("OK" to users.toList()))

            }
        }


    }
}


