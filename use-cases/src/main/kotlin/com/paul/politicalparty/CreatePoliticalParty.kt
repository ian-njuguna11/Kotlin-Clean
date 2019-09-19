package com.paul.politicalparty

import com.paul.AlreadyExistsException
import com.paul.entity.PoliticalParty
import com.paul.repos.IPoliticalPartyRepository
import java.util.logging.Logger

class CreatePoliticalParty (
    var party: PoliticalParty,
    var partyRepository: IPoliticalPartyRepository<PoliticalParty, Int>
){

    val LOG = Logger.getLogger(CreatePoliticalParty::class.java.name)

    fun execute(): PoliticalParty{
        if (existingPartyWithName()){
            LOG.warning("A party with the name ${party.name} already exists")
            throw AlreadyExistsException("A party with the name ${party.name} already exists")
        }
        return partyRepository.create(party) as PoliticalParty
    }

    private fun existingPartyWithName(): Boolean{
        val allParties = partyRepository.findAll()
        for(p in allParties){  if (p.name == party.name) return true  }
        return false
    }

}