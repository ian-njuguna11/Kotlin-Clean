package com.weqe.models

import org.jetbrains.exposed.dao.IntIdTable


object Post: IntIdTable("posts"){
    val user = reference("user", User)
    val content = varchar("content", 10000)
}