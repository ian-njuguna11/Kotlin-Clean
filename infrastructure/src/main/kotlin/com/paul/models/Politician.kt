package com.paul.models

import org.jetbrains.exposed.dao.LongIdTable

object Politician:LongIdTable("politicians") {
    var userId = long("user_id").uniqueIndex()
    var politicalPostId = long("political_post_id")
}