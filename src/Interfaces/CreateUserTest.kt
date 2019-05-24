package Interfaces

import domain.User
import org.junit.Assert.*

class CreateUserTest {

    var createGoodUser: CreateUser? = null
    var createBadUser: CreateUser? = null

    @org.junit.Before
    fun setUp(){
//        all the fields are properly put for this user
        val good_user = User(
                "paul", "paul1tw1@gmail.com", "Bluesky@1234", 23
        )
        this.createGoodUser = CreateUser(good_user)

//        all the fields for this user are wrongly written
        val bad_user = User(
                "Samual Njoroge", "sam.com", "blue", 130
        )

        this.createBadUser = CreateUser(bad_user)

    }

    @org.junit.Test
    fun testValidEmail() {
        assertEquals(this.createGoodUser!!.validateEmail(), true)
    }

    @org.junit.Test
    fun testInvalidEmail() {
        assertEquals(this.createBadUser!!.validateEmail(), false)
    }

    @org.junit.Test
    fun testValidUsername(){
        assertEquals(this.createGoodUser!!.validateUsername(), true)
    }

    @org.junit.Test
    fun testInvalidUsername(){
        assertEquals(this.createBadUser!!.validateUsername(), false)
    }

    @org.junit.Test
    fun testValidPassword(){
        assertEquals(this.createGoodUser!!.validatePassword(), true)
    }

    @org.junit.Test
    fun testInvalidPassword(){
        assertEquals(this.createBadUser!!.validatePassword(), false)
    }

}