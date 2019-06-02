package com.paul.UserInterfacesTest

import com.paul.InitDb
import com.paul.UserInterfaces.CreateUser
import com.paul.entity.UserDataClass
import org.junit.Test
import org.junit.Before
import org.junit.After
import kotlin.test.assertEquals

class CreateUserTest {

    var validUser: UserDataClass ?= null
    var invalidUser: UserDataClass ?= null
    // parameters for invalidUser will be changed as the prorgam progresses

    var createUser: CreateUser ?= null
    var db: InitDb ?= null

    @Before
    fun setUp(){

        db = InitDb(dbType = "test")
        db!!.createAllTables()

        invalidUser = UserDataClass(
            "weqe",
            "weqe@weqe.com",
            "5trangerThing@",
            45
        )

        validUser = UserDataClass(
            "weqe",
            "weqe@weqe.com",
            "5trangerThing@",
            45
        )

    }

    @After
    fun tearDown(){
        db!!.dropAllTables()
    }

    @Test fun testValidUserCreate(){

        createUser = CreateUser(validUser!!, "test")
        assertEquals(createUser!!.addUser(), "success")

    }

    @Test fun testInvalidUserEmail(){

        invalidUser!!.email = "weqe.com"
        createUser = CreateUser(invalidUser!!, "test")
        assertEquals(createUser!!.addUser(), "Enter a valid email")

    }

    @Test fun testInvalidUserUsername(){

        invalidUser!!.username = "thisreallylongusername"
        createUser = CreateUser(invalidUser!!, "test")
        assertEquals(createUser!!.addUser(), "The username cannot be more than 10 characters long")

    }

    @Test fun testInvalidPassword(){

        invalidUser!!.password = "pass"
        createUser = CreateUser(invalidUser!!, "test")
        assertEquals(createUser!!.addUser(), "Keep a stronger password")

    }

    @Test fun testInvalidAge() {

        invalidUser!!.age = 101
        createUser = CreateUser(invalidUser!!, "test")
        assertEquals(createUser!!.addUser(), "Age has to be between 1 and 100 years old")

    }

    @Test fun testExistingEmail(){

        createUser = CreateUser(validUser!!, "test")
        createUser!!.addUser()

        assertEquals( createUser!!.addUser(), "user already exists")

    }

}