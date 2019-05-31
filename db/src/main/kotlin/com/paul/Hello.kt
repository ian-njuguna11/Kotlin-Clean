package com.paul

import com.paul.entity.UserDataClass
import com.paul.port.UserCRUD
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {

    val initDb = InitDb()
    initDb.createAllTables()


    val userCrud = UserCRUD()
}

