package com.paul.models

import org.jetbrains.exposed.dao.LongIdTable

object Politician:LongIdTable("politicians") {
    var userId = long("user_id").uniqueIndex()
    var politicalPartyId = long("political_party_id")
}