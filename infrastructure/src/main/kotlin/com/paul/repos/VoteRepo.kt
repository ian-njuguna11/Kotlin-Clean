package com.paul.repos

import com.paul.entity.VoteDataClass
import com.paul.models.PoliticalPost
import com.paul.models.Politician
import com.paul.models.Vote
import com.paul.port.VoteDoesNotExistException
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class VoteRepo (
    private val userRepo: UserRepo,
    private val politicianRepo: PoliticianRepo,
    private val politicalPostRepo: PoliticalPostRepo
): BaseRepo(){

    fun create(vote: VoteDataClass){
        userRepo.findUserById(vote.userId)
        politicianRepo.findPoliticianById(vote.politicianId)
        politicalPostRepo.findPoliticalPostById(vote.politicalPostId)

        transaction {
            Vote.insert {
                it[userId] = vote.userId
                it[politicalPostId] = vote.politicalPostId
                it[politicianId] = vote.politicianId
            }
        }

    }

    fun findVoteById(id: Long): HashMap<String, String>{
        val voteInfo: HashMap<String, String> = HashMap()

        transaction {
            Vote.select {
                Vote.id eq id
            }.withDistinct().map {
                voteInfo["id"] = it[Vote.id].value.toString()
                val user = userRepo.findUserById(it[Vote.userId])
                val politician = userRepo.findUserById(
                    politicianRepo.findPoliticianById(it[Vote.politicianId])["id"]!!.toLong()
                )
                val post = politicianRepo.findPoliticianById(
                    it[Vote.politicianId]
                )["post"]
                voteInfo["username"] = """${user["firstName"]} ${user["lastName"]}"""
                voteInfo["politician"] = """${politician["firstName"]} ${politician["lastName"]}"""
                voteInfo["post"] = post!!
            }
        }

        if (voteInfo.isEmpty())
            throw VoteDoesNotExistException("vote with that ID could not be found")

        return voteInfo
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

    fun findUserVoteForPost(userId: Long, postId: Long){
        /*
        finds if user has voted for a particular post
        if method ends without throwing an error, there has been no vote cast yet, otherwise a vote has been cast
         */
        val voteInfo = HashMap<String, String>()

        transaction {
            Vote.select {
                Vote.userId eq userId
                Vote.politicalPostId eq postId
            }.map{
                voteInfo["id"] = it[Vote.id].value.toString()
            }
        }

        if (voteInfo.isNotEmpty())
            throw VoteDoesNotExistException("user has already voted for that post")
    }


    fun findNumberOfVotesAPoliticianHas(politicianId: Long): Int{
        val votesList = this.findAllPoliticianVotes(politicianId)
        return votesList.count()
    }

    fun findAllPoliticiansForSpecificPost(postId: Long): ArrayList<Long>{
        val politicians = ArrayList<Long>()

        transaction {
            Politician.select {
                Politician.politicalPostId eq postId
            }.map {
                politicians.add( it[Politician.id].value )
            }
        }

        return politicians
    }

    fun findSinglePostResults(postId: Long): HashMap<String, String>{
        val results = HashMap<String, String>()
        val politicians = findAllPoliticiansForSpecificPost(postId)
        politicalPostRepo.findPoliticalPostById(postId)

        for (politician in politicians){
            val politicianName = politicianRepo.findPoliticianById(politician)["firstName"] + " " + politicianRepo.findPoliticianById(politician)["lastName"]
            results[politicianName] = findNumberOfVotesAPoliticianHas(politician).toString()
        }

        return results
    }

    fun findAllPostsResults(): HashMap<String, HashMap<String, String>>{
        val results = HashMap<String, HashMap<String, String>>()

        transaction {
            PoliticalPost.selectAll().forEach {
                val postResults = findSinglePostResults(it[PoliticalPost.id].value)
                results[it[PoliticalPost.name]] = postResults
            }
        }

        return results
    }

}