package com.paul

import com.paul.entity.Voter
import com.paul.voter.CreateVoter
import org.junit.Before
import org.junit.Test
import kotlin.math.exp
import kotlin.test.assertEquals

class CreateVoterTest {

    val voterRepository = TVoterRepository()

    @Before
    fun setUp()
    {
        val voterRepository = TVoterRepository()
    }

    @Test
    fun testCreateVoter()
    {
        val voter = Voter(nationalId = "33575433", name = "Paul Wekesa")
        val createVoter = CreateVoter(voter, voterRepository)
        val createdVoter = createVoter.execute()
        val expectedCreatedVoter = Voter(nationalId = "33575433", id = 1, name = "Paul Wekesa")
        assertEquals(createdVoter, expectedCreatedVoter)
    }

    @Test(expected = AlreadyExistsException::class)
    fun createDuplicateVoters()
    {
        val voter = Voter(nationalId = "33575433", name = "Paul Wekesa")
        val createVoter = CreateVoter(voter, voterRepository)
        createVoter.execute()

        val duplicateVoter = Voter(nationalId = "33575433", name="Paul Wekesa")
        val createDuplicateVoter = CreateVoter(voter, voterRepository)
        createDuplicateVoter.execute()
    }

    @Test(expected = ValidationException::class)
    fun createVoterWithoutNationalId()
    {
        val voter = Voter()
        val createVoter = CreateVoter(voter, voterRepository)
        createVoter.execute()
    }

    @Test(expected = ValidationException::class)
    fun createVoterWithoutName()
    {
        val voter = Voter(nationalId = "33575433")
        val createVoter = CreateVoter(voter, voterRepository)
        createVoter.execute()
    }

}