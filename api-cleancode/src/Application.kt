package com.example

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.paul.SimpleJWT
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import com.paul.UserInterfaces.CreateUser
import com.paul.UserInterfaces.GetUser
import com.paul.entity.UserDataClass
import io.ktor.jackson.*
import io.ktor.features.*
import io.ktor.http.HttpStatusCode

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
        route("/"){
            get{
                val authToken = call.request.header("Authorization")!!.split(" ")[1]
                val algorithm = Algorithm.HMAC256("12095071")
                val verifier = JWT.require(algorithm)
                    .build()

                val jwt = verifier.verify(authToken)
                println(jwt)

                call.respond(mapOf("OK" to "cool"))

            }
        }

        route("/login"){



            // login route
            post {

                val algorithm = Algorithm.HMAC256("12095071")

                val loginUser = call.receive<UserDataClass>()


                val getUser = GetUser()
                val user = getUser.getUser(loginUser.email, loginUser.password)

                if (user.email == ""){

                    call.respond(
                        HttpStatusCode.NotFound, // 404
                        mapOf(
                            "ERROR" to "user email and password do not match"
                            )
                    )
                    return@post
                }

                val token = JWT.create()
                    .withClaim("id", user.id)
                    .sign(algorithm)

                call.respond(
                    HttpStatusCode.OK, // 200
                    mapOf(
                        "OK" to "successfully logged in",
                        "token" to token
                    )
                )


            }

        }


        route("/signup") {

            // SIGN UP ROUTE
            get {
                call.respond(mapOf("users" to users.toList()))
            }

            post {


                val postedUser = call.receive<UserDataClass>()

                val createUserObj = CreateUser(postedUser)
                val createResponse = createUserObj.addUser()

                if (createResponse != "success"){
                    call.respond(
                        HttpStatusCode.Unauthorized, // 401
                        mapOf("ERROR" to createResponse))
                    return@post
                }

                call.respond(mapOf("OK" to users.toList()))

            }
        }

    }
}


