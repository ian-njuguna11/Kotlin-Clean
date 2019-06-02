package com.paul

import com.paul.entity.UserDataClass
import com.paul.port.UserCRUD
import org.junit.After
import org.junit.Test
import org.junit.Before
import kotlin.test.assertEquals

class UserCRUDTest {

    var userCRUD: UserCRUD ?= null
    var db: InitDb ?= null
    var userOne: UserDataClass ?= null // user with valid parameters
    var userTwo: UserDataClass ?= null // user with invalid parameters

    @Before
    fun setUp(){

        db = InitDb(dbType = "test")
        db!!.createAllTables()
        userCRUD = UserCRUD(dbType="test")

        userOne = UserDataClass(
            "weqe",
            "weqe@weqe.com",
            "P@55word1234",
            30)

        userTwo = UserDataClass(
            "reallylongusername",
            "weqe.com",
            "P@55wo",
            101
        )
    }

    @After
    fun tearDown(){
        db!!.dropAllTables()
    }


    @Test
    fun testValidUser(){
        val createUserOneResponse = userCRUD!!.createUser(
            user_username = userOne!!.username, user_email = userOne!!.email,
            user_age = userOne!!.age, user_password = userOne!!.password
        )

        assertEquals(createUserOneResponse, true)
    }

    @Test
    fun testExistingEmail(){

        userCRUD!!.createUser(
            user_username = userOne!!.username, user_email = userOne!!.email,
            user_age = userOne!!.age, user_password = userOne!!.password
        )
        // create user with email weqe@weqe.com first time

        val createUserOneResponse = userCRUD!!.createUser(
            user_username = userOne!!.username, user_email = userOne!!.email,
            user_age = userOne!!.age, user_password = userOne!!.password
        )
        // try and create same user again. It should return false

        assertEquals(createUserOneResponse, false)
    }


}