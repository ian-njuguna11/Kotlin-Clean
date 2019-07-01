package com.paul.repos

import com.paul.entity.VoteDataClass
import com.paul.models.Vote
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class VoteRepo (
    private val userRepo: UserRepo,
    private val politicianRepo: PoliticianRepo,
    private val politicalPostRepo: PoliticalPostRepo
): BaseRepo(){

    fun create(vote: VoteDataClass){
        userRepo.findUserById(vote.userId)
        politicianRepo.findPoliticianById(vote.politicanId)
        politicalPostRepo.findPoliticalPostById(vote.politicalPostId)

        transaction {
            Vote.insert {
                it[userId] = vote.userId
                it[politicalPostId] = vote.politicalPostId
                it[politicianId] = vote.politicanId
            }
        }

    }

    fun findAllPoliticianVotes(politicianId: Long): ArrayList<String>{
        val politicianVotes = ArrayList<String>()

        transaction {
            Vote.select {
                Vote.politicianId eq politicianId
            }.forEach {
                politicianVotes.add(it[Vote.userId].toString())
            }
        }

        return politicianVotes
    }

}