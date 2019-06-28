import com.paul.initDb
import com.paul.mainModule
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class TestPolitician{

    @Test
    fun createPoliticianTest() = withTestApplication(Application::mainModule){
        initDb()

        // create user
        handleRequest(HttpMethod.Post, "/users"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{"email": "gidi@gmail.com", "firstName": "Gidi", "lastName": "Mukosi", "password": "thisi@paul12" }""")
        }.response.let{response ->
            assertEquals(HttpStatusCode.OK, response.status())
        }

        // create post
        handleRequest(HttpMethod.Post, "/political-posts"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{ "name" : "president", "description": "He leads the country"}""".trimIndent())
        }.response.let{response ->
            assertEquals(HttpStatusCode.Created, response.status())
        }

        // create politician from a user and vying for a specific post
        handleRequest(HttpMethod.Post, "/politicians"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{"userId":1,"politicalPostId":1}""")
        }.response.let { response ->
            assertEquals(HttpStatusCode.Created, response.status())
        }
    }

    @Test
    fun findExistingPoliticianByIdTest() = withTestApplication(Application::mainModule){
        createPoliticianTest()
        val responseList = listOf("""
            {"id":"1","firstName":"Gidi","lastName":"Mukosi","post":"president"}
        """.replace(" ", "").trimIndent())

        handleRequest(HttpMethod.Get, "/politicians/id/1"){
            addHeader("Accept", "application/json")
        }.response.let{response ->
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(Collections.sort(responseList), Collections.sort(response.content!!.lines()))
        }
    }

}