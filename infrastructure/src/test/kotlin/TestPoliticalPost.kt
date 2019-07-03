import com.paul.initDb
import com.paul.mainModule
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import org.hamcrest.CoreMatchers
import org.junit.Assert
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
            assertEquals(HttpStatusCode.NotAcceptable, response.status())
        }
    }

    @Test
    fun findExistingPoliticalPostByIdTest() = withTestApplication(Application::mainModule){
        createPoliticalPostTest()
        handleRequest(HttpMethod.Get, "/political-posts/id/1"){
            addHeader("Accept", "application/json")
        }.response.let{ response ->
            assertEquals(HttpStatusCode.OK, response.status())
        }
    }

    @Test
    fun findNonExistentPoliticalPostByIdTest() = withTestApplication(Application::mainModule){
        initDb()
        handleRequest(HttpMethod.Get, "/political-posts/id/1"){
            addHeader("Accept", "application/json")
        }.response.let{ response ->
            assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }

    @Test
    fun findPoliticalPostWithStringAsIdTest() = withTestApplication(Application::mainModule){
        createPoliticalPostTest()
        handleRequest(HttpMethod.Get, "/political-posts/id/this_id"){
            addHeader("Accept", "application/json")
        }.response.let { response ->
            assertEquals(HttpStatusCode.NotAcceptable, response.status())
        }
    }

    @Test
    fun findPoliticalPostByName() = withTestApplication(Application::mainModule){
        createPoliticalPostTest()
        handleRequest(HttpMethod.Get, "/political-posts/name/president"){
            addHeader("Accept", "application/json")
        }.response.let {response ->
            assertEquals(HttpStatusCode.OK, response.status())
            Assert.assertThat(response.content , CoreMatchers.containsString(""""name":"president""""))
        }
    }
}