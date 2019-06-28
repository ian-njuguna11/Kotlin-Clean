package com.paul

import com.paul.controllers.politicalPost
import com.paul.controllers.users
import com.paul.models.PoliticalPost
import com.paul.repos.PoliticalPostRepo
import com.paul.repos.UserRepo
import com.paul.models.User as UserMode
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import com.paul.models.User as UserModel

var userRepo = UserRepo()
var politicalPostRepo = PoliticalPostRepo()

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

    routing {
        users()
        politicalPost()
    }
}


fun initDb(){
    val dbName = System.getenv("DB_NAME") ?: ""
    val dbUser = System.getenv("DB_USER") ?: ""
    val dbPassword = System.getenv("DB_PASSWORD")

    Database.connect(
        "jdbc:postgresql://localhost:5432/$dbName?user=$dbUser&password=$dbPassword",
        driver="org.h2.Driver"
    )

    transaction {
        SchemaUtils.drop(UserModel)
        SchemaUtils.drop(PoliticalPost)

        SchemaUtils.create(UserModel)
        SchemaUtils.create(PoliticalPost)

    }

}