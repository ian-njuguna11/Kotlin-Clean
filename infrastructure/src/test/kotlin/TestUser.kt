import com.paul.initDb
import com.paul.mainModule
import io.ktor.application.Application
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.setBody
import kotlin.test.*

class TestUser{

    @Test
    fun createUserTest() = withTestApplication(Application::mainModule){
        initDb()
        handleRequest(HttpMethod.Post, "/users"){
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody("""{"email": "gidi@gmail.com", "firstName": "Gidi", "lastName": "Mukosi", "password": "thisi@paul12" }""")
        }.response.let{response ->
            assertEquals(HttpStatusCode.OK, response.status())
        }
    }


    @Test
    fun getAllUsersTest() = withTestApplication(Application::mainModule){
        createUserTest()
        val responseList = listOf("""
            { "1": { "firstName": "Gidi", "lastName": "Mukosi", "id": "1", "email": "gidi@gmail.com" } }
        """.replace(" ", "").trimIndent())
        handleRequest(HttpMethod.Get, "/users"){
            addHeader("Accept", "application/json")
        }.response.let { response ->
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals( responseList, response.content!!.lines())
        }
    }
    @Test
    fun getSingleUserTest() = withTestApplication(Application::mainModule){
        createUserTest()
        val responseList = listOf("""
            {"firstName": "Gidi","lastName": "Mukosi", "id" : "1", "email" : "gidi@gmail.com" }"""
            .replace(" ", "").trimIndent())

        handleRequest(HttpMethod.Get, "/users/1"){
            addHeader("Accept", "application/json")
        }.response.let { response ->
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(responseList, response.content!!.lines())
        }
    }

    @AfterTest
    fun tearDown(){
        initDb()
    }

}