package com.paul

import com.paul.UserUseCases.AddUser
import com.paul.entity.UserDataClass
import com.paul.port.InvalidPasswordException
import com.paul.port.UserAlreadyExistsException
import org.junit.Before
import org.junit.Test

class AddUserTest {
    private var usersArray = ArrayList<UserDataClass>()
    var newUser = UserDataClass()

    @Before
    fun setUp() {

        val userList = listOf(
            UserDataClass(
                1, "paul@paul.com", "Paul", "Wekesa", 33575433, "Password@123"
            ),
            UserDataClass(
                2, "abc@abc.com", "Abc", "Def", 892993, "ThisPassword@341"
            )
        )

        usersArray.addAll(userList as List<UserDataClass>)

        newUser = UserDataClass(
            3, "new@new.com", "New", "User", 345599, "NewPassword@456"
        )

    }

    @Test
    fun testValidUser(){

        val addUserObj = AddUser(newUser, usersArray)
        addUserObj.run()

    }

    @Test(expected = UserAlreadyExistsException::class)
    fun testAlreadyExistingUser(){
        newUser.id = 1
        val addUserObj = AddUser(newUser, usersArray)
        addUserObj.run()
    }

    @Test(expected = InvalidPasswordException::class)
    fun testWithInvalidPassword(){

        newUser.password = "this"
        val addUserObj = AddUser(newUser, usersArray)
        addUserObj.run()

    }
}