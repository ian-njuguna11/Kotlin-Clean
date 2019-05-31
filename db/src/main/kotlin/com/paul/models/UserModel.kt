package com.paul.models

import org.jetbrains.exposed.dao.IntIdTable
import kotlin.math.max

object UserModel: IntIdTable("users") {
    val username = varchar("username", 50)
    val email = varchar("email", 100).uniqueIndex()
    val password = varchar("password", 100)
    val age = integer("age")
}