package com.weqe.repos


import com.weqe.models.User
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class CreateUser(
    private val newName: String,
    private val newEmail: String,
    private val newPassword: String
) {
    fun execute(){
        Database.connect(
            "jdbc:postgresql://localhost:5432/postgres_demo?user=strath&password=5trathm0re",
            driver="org.h2.Driver"
        )

        transaction {
            addLogger(StdOutSqlLogger)

            User.insert {
                it[name] = newName
                it[email] = newEmail
                it[password] = newPassword
            }

        }
    }
}


