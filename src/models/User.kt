package com.weqe.models

import org.jetbrains.exposed.dao.IntIdTable


object User: IntIdTable("users") {
    var name = varchar("name", 50)
    var email = varchar("email", 50).uniqueIndex()
    var password = varchar("password", 100)
}
