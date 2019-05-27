package com.paul

import kotlin.test.assertEquals
import org.junit.Assert.*

class HelloTest {
    var createGoodUser: CreateUser? = null
    var createBadUser: CreateUser? = null

    @org.junit.Before
    fun setUp(){
//        all the fields are properly put for this user
        val goodUser = User(
            "paul", "paul1tw1@gmail.com", "Bluesky@1234", 23
        )
        this.createGoodUser = CreateUser(goodUser)

//        all the fields for this user are wrongly written
        val badUser = User(
            "Samual Njoroge", "sam.com", "blue", 130
        )

        this.createBadUser = CreateUser(badUser)

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
