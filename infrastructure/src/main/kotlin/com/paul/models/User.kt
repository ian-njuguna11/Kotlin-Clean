package com.paul.models

import org.jetbrains.exposed.dao.IntIdTable

object User: IntIdTable("users"){
    var email = varchar("email", 50)
    var password = varchar("password", 100)
    var firstName = varchar("firstName", 50)
    var lastName = varchar("lastName", 50)

}
