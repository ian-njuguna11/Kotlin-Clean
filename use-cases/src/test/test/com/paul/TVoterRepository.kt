package com.paul

import com.paul.entity.Voter
import com.paul.repos.IVoterRepository
import java.util.logging.Logger

class TVoterRepository : TBaseRepository<Voter, Int>(), IVoterRepository<Voter, Int>{

    val LOG = Logger.getLogger(TVoterRepository::class.java.name)

    override fun findByNationalId(nationalId: String): Voter? {
        baseMap.forEach {
            val voter = it.value as Voter
            if (voter.nationalId == nationalId) {
                LOG.info("user with national ID $nationalId found")
                return voter
            }
        }
        return null
    }

}