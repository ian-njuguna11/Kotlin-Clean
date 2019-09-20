package com.paul.candidate

import com.paul.entity.Candidate
import com.paul.entity.Vote
import com.paul.repos.IVoteRepository


class CountCandidateVotes(
    val candidate: Candidate,
    val votesRepository: IVoteRepository<Vote, Int>
){
    fun execute(): Int{ return votesRepository.findAllByCandidateId(candidate.id!!).count() }
}