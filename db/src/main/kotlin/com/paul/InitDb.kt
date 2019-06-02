package com.paul

import com.paul.models.UserModel
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class InitDb(dbType: String = "") {

    init {

        if ( dbType == "test"){
            Database.connect(
                "jdbc:postgresql://localhost:5432/kotlin_clean_test",
                driver = "org.postgresql.Driver",
                user = System.getenv("DB_USER"), password = System.getenv("DB_PASSWORD")
            )
        }
        else{
            Database.connect(
                "jdbc:postgresql://localhost:5432/kotlin_clean",
                driver = "org.postgresql.Driver",
                user = System.getenv("DB_USER"), password = System.getenv("DB_PASSWORD")
            )
        }
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

    fun dropAllTables(){
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.drop(UserModel)
        }
    }

}