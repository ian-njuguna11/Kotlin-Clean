package com.paul

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Before

class ValidatorTest {

    var validator: Validator ?= null
    var userOne: UserDataClass ?= null      // tests for a user with valid properties
    var userTwo: UserDataClass ?= null      // tests for a user with invalid properties

    @Before
    fun setUp(){
        validator = Validator()

        userOne = UserDataClass("weqe", "weqe@weqe.com", "Str0ngPassword", 30)
        userTwo = UserDataClass("longusername", "weqe.com", "this", 101)

    }

    @Test
    fun testValidEmail(){
        assertEquals(validator!!.validateEmail(userOne!!.email), true)
    }

    @Test
    fun testInvalidEmail(){
        assertEquals(validator!!.validateEmail(userTwo!!.email), false)
    }

    @Test
    fun testValidUsername(){
        assertEquals(validator!!.validateUsername(userOne!!.username), true)
    }

    @Test
    fun testInvalidUsername(){
        assertEquals(validator!!.validateUsername(userTwo!!.username), false)
    }

    @Test
    fun testValidPassword(){
        assertEquals(validator!!.validatePassword(userOne!!.password), true)
    }

    @Test
    fun testInvalidPassword(){
        assertEquals(validator!!.validatePassword(userTwo!!.password), false)
    }

}
