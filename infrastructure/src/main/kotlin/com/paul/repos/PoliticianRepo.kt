package com.paul.repos

import com.paul.entity.PoliticianDataClass
import com.paul.models.Politician
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class PoliticianRepo: BaseRepo() {
    val userRepo = UserRepo()
    val politicalPostRepo = PoliticalPostRepo()

    fun create(politician: PoliticianDataClass){
        transaction {
            Politician.insert {
                it[userId] = politician.userId
                it[politicalPartyId] = politician.politicalPostId
            }
        }
    }

    fun findPoliticianById(id: Long): HashMap<String, String>{
        val politician = HashMap<String, String>()

        transaction {
            Politician.select {
                Politician.id eq id
            }.map {
                politician["id"] = it[Politician.id].toString()
                politician["firstName"] = userRepo.findUserById(id)["firstName"]!!
                politician["lastName"] = userRepo.findUserById(it[Politician.userId])["lastName"]!!
                politician["post"] = politicalPostRepo.findPoliticalPostById(it[Politician.politicalPartyId])["name"]!!
            }
        }

        return politician
    }

}