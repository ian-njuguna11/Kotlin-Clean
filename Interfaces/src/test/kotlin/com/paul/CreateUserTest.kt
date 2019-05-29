package com.paul

import com.paul.entity.UserDataClass
import kotlin.test.assertEquals
import org.junit.Assert.*

class CreateUserTest {

    var userOne: UserDataClass? = null
    var userTwo: UserDataClass? = null

    @org.junit.Before
    fun setUp(){

        userOne = UserDataClass("paul", "paul@paul.com", "paul_password", 30)
        // userOne is a valid user that is created and inserted into the system

        userTwo = UserDataClass("gidi", "gidi@gidi.com", "gidi_password", 40)
    }

    @org.junit.Test
    fun testValidUser(){

        var createUser = CreateUser(this.userOne!!)
        assertEquals(createUser.addUser(), "success")

    }

    @org.junit.Test
    fun testInvalidAge(){

        userTwo!!.age = 101
        val createUser = CreateUser(this.userTwo!!)
        assertEquals(createUser.addUser(), "Age has to be between 1 and 100 years old")

    }

    @org.junit.Test
    fun testInvalidEmail(){
        userTwo!!.email = "gidi.com"
        val createUser = CreateUser(this.userTwo!!)
        assertEquals(createUser.addUser(), "Enter a valid email")
    }

    @org.junit.Test
    fun testInvalidPassword(){
        userTwo!!.password = "12"
        val createUser = CreateUser(this.userTwo!!)
        assertEquals(createUser.addUser(), "Keep a stronger password")
    }

    @org.junit.Test
    fun testExistingUser(){
        // try to add a user with same email twice
        val createUser = CreateUser(this.userOne!!)
        createUser.addUser()

        // add the same user for the second time
        assertEquals(createUser.addUser(), "User already found")

    }



}
