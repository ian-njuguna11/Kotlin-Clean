package com.paul.voter

import com.paul.AlreadyExistsException
import com.paul.ValidationException
import com.paul.entity.Voter
import com.paul.repos.IVoterRepository
import java.util.logging.Logger

class CreateVoter(
    var voter: Voter,
    var voterRepository: IVoterRepository<Voter, Int>
){
    var LOG = Logger.getLogger(CreateVoter::class.java.name)

    fun execute():Voter {
        if (existingVoterWithNationalId()){
            throw AlreadyExistsException("user with national ID ${voter.nationalId} already exists")
        } else if (!validateFields()){
            throw ValidationException("Unable to validate fields in ${voter.toMap()}")
        }

        return voterRepository.create(voter) as Voter
    }

    private fun existingVoterWithNationalId(): Boolean{
        voterRepository.findByNationalId(voter.nationalId)?.let {
            LOG.warning("user with national ID ${voter.nationalId} already exists")
            return true
        }?.run {}
        return false
    }

    private fun validateFields(): Boolean {
        // all fields are valid only when a true is returned
        if (voter.nationalId == ""){
            LOG.warning("National ID cannot be left blank")
            return false
        } else if (voter.name == ""){
            LOG.warning("Name field cannot be left blank")
            return false
        }
        return true
    }

}