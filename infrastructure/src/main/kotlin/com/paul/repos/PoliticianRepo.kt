package com.paul.repos

import com.paul.entity.PoliticianDataClass
import com.paul.models.Politician
import com.paul.port.PoliticianDoesNotExistException
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PoliticianRepo: BaseRepo() {
    private val userRepo = UserRepo()
    private val politicalPostRepo = PoliticalPostRepo()

    fun create(politician: PoliticianDataClass){
        transaction {
            Politician.insert {
                it[userId] = politician.userId
                it[politicalPostId] = politician.politicalPostId
            }
        }
    }

    fun findAll(): HashMap<String, HashMap<String, String>>{

        val politicians = HashMap<String, HashMap<String, String>>()

        transaction {
            Politician.selectAll().forEach {
                val politicianArray = HashMap<String, String>()
                val user = userRepo.findUserById(it[Politician.userId])
                val politicalPost = politicalPostRepo.findPoliticalPostById(it[Politician.politicalPostId])

                politicianArray["userId"] = user["id"].toString()
                politicianArray["postId"] = politicalPost["id"].toString()
                politicianArray["name"] = """ ${user["firstName"]} ${user["lastName"]} """
                politicianArray["postName"] = """${politicalPost["name"]}"""

                politicians[it[Politician.id].toString()] = politicianArray
            }
        }
        return politicians
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
                politician["post"] = politicalPostRepo.findPoliticalPostById(it[Politician.politicalPostId])["name"]!!
            }
        }

        if (politician.isEmpty())
            throw PoliticianDoesNotExistException("politician could not be found")

        return politician
    }

}