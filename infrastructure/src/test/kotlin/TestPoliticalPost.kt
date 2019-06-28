import com.paul.initDb
import com.paul.mainModule
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class TestPoliticalPost {

    @Test
    fun createPoliticalPostTest() = withTestApplication(Application::mainModule){
        initDb()
        handleRequest(HttpMethod.Post, "/political-posts"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{ "name" : "president", "description": "He leads the country"}""".trimIndent())
        }.response.let{response ->
            assertEquals(HttpStatusCode.Created, response.status())
        }
    }

    @Test
    fun createPostsWithSameNameTest() = withTestApplication (Application::mainModule){
        createPoliticalPostTest()
        handleRequest(HttpMethod.Post, "/political-posts"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{ "name" : "president", "description": "He leads the country"}""".trimIndent())
        }.response.let{response ->
            println(response.content)
            assertEquals(HttpStatusCode.NotAcceptable, response.status())
        }
    }

}