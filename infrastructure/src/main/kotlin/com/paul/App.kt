package com.paul

import com.paul.controllers.users
import com.paul.repos.UserRepo
import com.paul.models.User as UserModel
import com.paul.validators.UserValidator
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.NotFoundException
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

var userRepo = UserRepo()

@KtorExperimentalAPI
fun main(){
    initDb()
    embeddedServer(Netty, port = 8081, module = Application::mainModule).start(wait = true)
}

@KtorExperimentalAPI
fun Application.mainModule(){

    install(ContentNegotiation){
        jackson {  }
    }

//    install(StatusPages){
//        status(HttpStatusCode.NotFound){
//            call.respond(mapOf("error" to "page not found"))
//        }
//    }

    routing {
        users()
    }
}


fun initDb(){
    Database.connect(
        "jdbc:postgresql://localhost:5432/postgres_demo?user=strath&password=5trathm0re",
        driver="org.h2.Driver"
    )

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.drop(UserModel)
        SchemaUtils.create(UserModel)
    }
}