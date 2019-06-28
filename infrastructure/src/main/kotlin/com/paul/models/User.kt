package com.paul.models

import org.jetbrains.exposed.dao.LongIdTable

object User: LongIdTable("users"){
    var email = varchar("email", 50).uniqueIndex()
    var password = varchar("password", 100)
    var firstName = varchar("firstName", 50)
    var lastName = varchar("lastName", 50)

}
