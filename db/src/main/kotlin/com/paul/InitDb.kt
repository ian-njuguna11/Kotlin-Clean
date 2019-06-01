package com.paul

import com.paul.models.UserModel
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class InitDb {

    constructor() {
        Database.connect(
            "jdbc:postgresql://localhost:5432/kotlin_clean",
            driver = "org.postgresql.Driver",
            user = "postgres", password = "Omwene11@"
        )
    }

    fun createTable(table: IntIdTable){
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(table)
        }
    }

    fun createAllTables(){

        this.createTable(UserModel)

    }

}