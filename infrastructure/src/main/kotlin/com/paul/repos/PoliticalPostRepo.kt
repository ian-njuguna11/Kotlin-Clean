package com.paul.repos

import com.paul.entity.PoliticalPostDataClass
import com.paul.models.PoliticalPost
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PoliticalPostRepo: BaseRepo(){

    fun create(politicalPost: PoliticalPostDataClass){
        transaction {
            PoliticalPost.insert {
                it[name] = politicalPost.name
                it[description] = politicalPost.description
            }
        }
    }

    fun findPostByName(name: String): HashMap<String, String>{

        val politicalPost: HashMap<String, String> = HashMap()

        transaction {
            PoliticalPost.select {
                PoliticalPost.name eq name
            }.map{
                politicalPost["id"] = it[PoliticalPost.id].toString()
                politicalPost["name"] = it[PoliticalPost.name]
                politicalPost["description"] = it[PoliticalPost.description]
            }
        }
        return politicalPost
    }

    fun findAllPoliticalPosts(): HashMap<String, HashMap<String, String>>{
        val politicalPosts: HashMap<String, HashMap<String, String>> = HashMap()

        transaction {
            PoliticalPost.selectAll().forEach {
                val politicalPostArray: HashMap<String, String> = HashMap()
                politicalPostArray["id"] = it[PoliticalPost.id].toString()
                politicalPostArray["name"] = it[PoliticalPost.name]
                politicalPostArray["description"] = it[PoliticalPost.description]
                politicalPosts[it[PoliticalPost.id].toString()] = politicalPostArray
            }
        }

        return politicalPosts

    }

    fun findRepoById(id: Long): HashMap<String, String>{
        val politicalPost = HashMap<String, String>()


        transaction {
            PoliticalPost.select{
                PoliticalPost.id eq id
            }.map {
                politicalPost["id"] = it[PoliticalPost.id].toString()
                politicalPost["name"] = it[PoliticalPost.name]
            }
        }

        return politicalPost
    }
}