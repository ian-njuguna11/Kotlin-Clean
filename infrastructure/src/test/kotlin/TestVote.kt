import com.paul.entity.PoliticalPostDataClass
import com.paul.entity.PoliticianDataClass
import com.paul.entity.UserDataClass
import com.paul.entity.VoteDataClass
import com.paul.initDb
import com.paul.mainModule
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class TestVote {

    private var user = UserDataClass()
    private var politicalPost = PoliticalPostDataClass()
    private var politician = PoliticianDataClass()
    private var vote = VoteDataClass()

    @Before
    fun setUp(){

        user = UserDataClass(
            email = "abc@abc.com",
            firstName = "abc",
            lastName = "def",
            password = "Password@123"
        )

        politicalPost = PoliticalPostDataClass(
            name = "president",
            description = "leader of the nation"
        )

        politician = PoliticianDataClass(
            userId = 1L,
            politicalPostId = 1L
        )

        vote = VoteDataClass(
                userId = 1L,
            politicalPostId = 1L,
            politicianId = 1L
        )
    }


    @Test
    fun createVoteTest() = withTestApplication(Application::mainModule){
        initDb()

        // create the user
        handleRequest(HttpMethod.Post, "/users"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{"email": "${user.email}", "firstName": "${user.firstName}",
                |"lastName": "${user.lastName}", "password": "${user.password}" }""".trimMargin())
        }

        // create political post
        handleRequest(HttpMethod.Post, "/political-posts"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{ "name" : "${politicalPost.name}", "description": "${politicalPost.description}"}""".trimIndent())
        }

        // create politician
        handleRequest(HttpMethod.Post, "/politicians"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{"userId":${politician.userId},"politicalPostId":${politician.politicalPostId}}""")
        }

        // create vote
        handleRequest(HttpMethod.Post, "/votes"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{"userId":"${vote.userId}", "politicalPostId":"${vote.politicalPostId}",
                |"politicianId":${vote.politicianId}}""".trimMargin())
        }.response.let{response ->
            assertEquals(response.status(), HttpStatusCode.Created)
        }

    }


    @Test
    fun findAllVotesTest() = withTestApplication(Application::mainModule){
        initDb()

        // create the user
        handleRequest(HttpMethod.Post, "/users"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{"email": "${user.email}", "firstName": "${user.firstName}",
                |"lastName": "${user.lastName}", "password": "${user.password}" }""".trimMargin())
        }

        // create political post
        handleRequest(HttpMethod.Post, "/political-posts"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{ "name" : "${politicalPost.name}", "description": "${politicalPost.description}"}""".trimIndent())
        }

        // create politician
        handleRequest(HttpMethod.Post, "/politicians"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{"userId":${politician.userId},"politicalPostId":${politician.politicalPostId}}""")
        }

        // create vote
        handleRequest(HttpMethod.Post, "/votes"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{"userId":"${vote.userId}", "politicalPostId":"${vote.politicalPostId}",
                |"politicianId":${vote.politicianId}}""".trimMargin())
        }

        handleRequest(HttpMethod.Get, "/votes-tally"){
            addHeader("Accept", "application/json")
        }.response.let {response ->
            assertEquals("""{"president":{"abc def":"1"}}""", response.content)
            assertEquals(response.status(), HttpStatusCode.OK)
        }

        handleRequest(HttpMethod.Get, "/post-votes-tally/1"){
            addHeader("Accept", "application/json")
        }.response.let{response ->
            assertEquals("""{"abc def":"1"}""", response.content)
            assertEquals(response.status(), HttpStatusCode.OK)
        }

    }

    @After
    fun dropAllData() = withTestApplication(Application::mainModule){
        initDb()
    }

}

