package com.paul.repos

import org.jetbrains.exposed.sql.Database

open class BaseRepo{

    init {
        Database.connect(
            "jdbc:postgresql://localhost:5432/postgres_demo?user=strath&password=5trathm0re",
            driver="org.h2.Driver"
        )
    }
}