package com.paul

import com.paul.entity.PoliticalParty
import com.paul.politicalparty.CreatePoliticalParty
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CreatePoliticalPartyTest {

    var politicalPartyRepo = TPoliticalPartyRepository()

    @Before
    fun setUp(){
        politicalPartyRepo = TPoliticalPartyRepository()
    }

    @Test
    fun testCreateNonExistingPoliticalParty(){
        val party = PoliticalParty(name = "Democrat")
        val expectedCreatedPoliticalParty = PoliticalParty(name = "Democrat", id = 1)
        val createPoliticalParty = CreatePoliticalParty(party, politicalPartyRepo)
        assertEquals(createPoliticalParty.execute(), expectedCreatedPoliticalParty)
    }

    @Test(expected = AlreadyExistsException::class)
    fun testCreateAlreadyExistingPoliticalParty(){
        val originalPoliticalParty = PoliticalParty(name="Democrat")
        val duplicatePoliticalParty = PoliticalParty(name="Democrat")
        val createOriginalPoliticalParty = CreatePoliticalParty(originalPoliticalParty, politicalPartyRepo)
        val createDuplicatePoliticalParty = CreatePoliticalParty(duplicatePoliticalParty, politicalPartyRepo)
        createOriginalPoliticalParty.execute()
        createDuplicatePoliticalParty.execute()
    }


}