package com.paul.candidate

import com.paul.AlreadyExistsException
import com.paul.DoesNotExistException
import com.paul.entity.Candidate
import com.paul.entity.PoliticalParty
import com.paul.entity.Post
import com.paul.repos.ICandidateRepository
import com.paul.repos.IPoliticalPartyRepository
import com.paul.repos.IPostRepository
import java.util.logging.Logger

class CreateCandidate (
    var candidate: Candidate,
    var partyName: String = "",
    var postName: String = "",
    var candidateRepo: ICandidateRepository<Candidate, Int>,
    var postRepo: IPostRepository<Post, Int>,
    var partyRepo: IPoliticalPartyRepository<PoliticalParty, Int>
){

    val LOG = Logger.getLogger(CreateCandidate::class.java.name)

    fun execute(): Candidate {
        LOG.info("Attempting to create candidate ${candidate.toString()}")
        LOG.info("with party name $partyName and post name $postName")

        if (!partyExists()){
            LOG.warning("Party Specified Does not exist in the system")
            throw DoesNotExistException("Party you have specified does not exist in the system")
        } else if (!postExists()){
            LOG.warning("Post specified does not exist in the system")
            throw DoesNotExistException("Post you have specified does not exist in the system")
        } else if (candidateWithSameNationalIdExists()){
            LOG.warning("Candidate with national ID ${candidate.nationalId} already exists in the system")
            throw AlreadyExistsException("Candidate with national ID ${candidate.nationalId} already exists in the system")
        }

        candidate.politicalPartyId = partyRepo.findByName(partyName)?.id!!
        candidate.politicalPartyName = partyName
        candidate.politicalPostId = postRepo.findByName(postName)?.id!!
        candidate.politicalPostName = postName

        val createdCandidate = candidateRepo.create(candidate)
        LOG.info("Candidate ${createdCandidate.toMap()} has been successfully created")
        return createdCandidate as Candidate
    }

    private fun partyExists(): Boolean{
        // looks if the name of the party has been specified and looks for it in the party repository
        // if the name is not specified / the name is  not found in the repo, we then look based on the ID
        if (partyName != "") partyRepo.findByName(partyName)?.let { return true }

        partyRepo.findById(candidate.politicalPartyId)?.let { return true }?.run { return false }
        return false
    }

    private fun postExists(): Boolean{
        // looks if the name of the post has been specified. If it has been specified, it looks for the post in the party repository
        // if the name is not specified / the name is  not found in the repo, we then look based on the id
        if (postName != "")
            postRepo.findByName(postName)?.let { return true }

        postRepo.findById(candidate.politicalPostId)?.let { return true }?.run{ return false }
        return false
    }

    private fun candidateWithSameNationalIdExists(): Boolean{
        // looks for a candidate with a similar national_id exists in the system
        candidateRepo.findByNationalId(candidate.nationalId)?.let { return true }?.run{ }
        return false
    }

}