package com.paul.models

import org.jetbrains.exposed.dao.LongIdTable

object Vote : LongIdTable("votes"){
    var userId = long("user_id")
    var politicalPostId = long("political_post_id")
    val politicianId = long("politician_id")
}