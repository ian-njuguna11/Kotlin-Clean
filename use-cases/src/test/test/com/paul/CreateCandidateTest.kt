package com.paul

import com.paul.candidate.CreateCandidate
import com.paul.entity.Candidate
import com.paul.entity.PoliticalParty
import com.paul.entity.Post
import com.paul.politicalparty.CreatePoliticalParty
import com.paul.post.CreatePost
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CreateCandidateTest
{

    val postRepository = TPostRepositoryT()
    val partyRepository = TPoliticalPartyRepository()
    val candidateRepository = TCandidateRepository()

    @Before
    fun setUp()
    {
        val postRepository = TPostRepositoryT()
        val partyRepository = TPoliticalPartyRepository()
        val candidateRepository = TCandidateRepository()
    }

    @Test
    fun testCreateCandidate()
    {

        // create post
        val post = Post(name = "presidency")
        val createPost = CreatePost(post, postRepository)
        val createdPost = createPost.execute()

        // create party
        val party = PoliticalParty(name = "Democrat")
        val createParty = CreatePoliticalParty(party, partyRepository)
        val createdParty = createParty.execute()

        // create candidate
        val candidate = Candidate(name = "Obama")
        val createCandidate = CreateCandidate(
            candidate, "Democrat", "presidency", candidateRepository, postRepository, partyRepository
        )
        val createdCandidate = createCandidate.execute()
        val expectedCreatedCandidate = Candidate(
            id=1, politicalPartyName = "Democrat", politicalPostName = "presidency", politicalPostId = 1,
            politicalPartyId = 1, name="Obama"
        )
        assertEquals(createdCandidate, expectedCreatedCandidate)

    }

    @Test(expected = AlreadyExistsException::class)
    fun testCreateDuplicateCandidate(){

        // create post
        val post = Post(name = "presidency")
        val createPost = CreatePost(post, postRepository)
        val createdPost = createPost.execute()

        // create party
        val party = PoliticalParty(name = "Democrat")
        val createParty = CreatePoliticalParty(party, partyRepository)
        val createdParty = createParty.execute()

        // create candidate
        val candidate = Candidate(name = "Obama")
        val createCandidate = CreateCandidate(
            candidate, "Democrat", "presidency", candidateRepository, postRepository, partyRepository
        )
        createCandidate.execute()

        // create duplicate candidate
        val duplicateCandidate = Candidate(name = "Obama")
        val createDuplicateCandidate = CreateCandidate(
            duplicateCandidate, "Democrat", "presidency", candidateRepository, postRepository, partyRepository
        )
        createDuplicateCandidate.execute()

    }

}