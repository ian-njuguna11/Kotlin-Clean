package com.paul

import com.paul.port.UserCRUD

fun main(args: Array<String>) {

    val initDb = InitDb()
    initDb.createAllTables()

    val userCRUD = UserCRUD()
    println(userCRUD.getSingleUser("paul@paul.com", "joy_password"))
    println("##")
}

