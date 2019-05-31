package com.paul.models

import org.jetbrains.exposed.dao.IntIdTable

object CityModel: IntIdTable() {
    val name = varchar("name", 50)
}