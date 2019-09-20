package com.paul

import com.paul.entity.Candidate
import com.paul.repos.ICandidateRepository


class TCandidateRepository: TBaseRepository<Candidate, Int>(),  ICandidateRepository<Candidate, Int>{
    override fun findAllByPoliticalPartyId(politicalPartyId: Int): ArrayList<Candidate> {
        val candidates = ArrayList<Candidate>()
        baseMap.forEach{
            val candidate = it.value as Candidate
            if (candidate.politicalPartyId == politicalPartyId) candidates.add(candidate)
        }
        return candidates
    }

    override fun findAllByPoliticalPostId(politicalPostId: Int): ArrayList<Candidate> {
        val candidates = ArrayList<Candidate>()
        baseMap.forEach {
            val candidate = it.value as Candidate
            if (candidate.politicalPostId == politicalPostId) candidates.add(candidate)
        }
        return candidates
    }

    override fun findByNationalId(nationalId: String): Candidate? {
        baseMap.forEach {
            val candidate = it.value as Candidate
            if (candidate.nationalId == nationalId) return candidate
        }
        return null
    }

}
