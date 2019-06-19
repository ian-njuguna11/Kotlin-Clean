package com.paul

import com.paul.controllers.users
import com.paul.models.User
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun main(){
    initDb()
    embeddedServer(Netty, port = 8081, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule(){

    install(ContentNegotiation){
        jackson {  }
    }

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

        SchemaUtils.create(User)
    }
}