package com.paul.models

import org.jetbrains.exposed.dao.LongIdTable

object PoliticalPost: LongIdTable("political_posts") {

    var name = varchar("name", 50).uniqueIndex()
    var description = text("description")

}
