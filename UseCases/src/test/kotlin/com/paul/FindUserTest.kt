package com.paul

import com.paul.UserUseCases.FindUser
import com.paul.Validators.UserValidations
import com.paul.entity.UserDataClass
import com.paul.port.BlankFieldException
import org.junit.Before
import org.junit.Test


class FindUserTest {

    var testUsers = ArrayList<UserDataClass>()
    var findUser: FindUser ?= null

    @Before
    fun initialSetUp() {

        val userList = listOf(

            UserDataClass(
                id = 1, email = "paul@paul.com", firstName = "Paul", lastName = "Wekesa",
                nationalId = 33575433, password = "5trathm0re@123"
            ),
            UserDataClass(
                id = 2, email = "abc@abc.com", firstName = "abc", lastName = "def",
                nationalId = 1102030, password = "Bluesky@123"
            )
        )
        testUsers.addAll(userList)
        val user = UserDataClass(
            id = 3, email = "gidi@gidi.com", firstName = "Gideon", lastName = "Mukosi", password = "LongPassword#",
            nationalId = 33442211
        )

        findUser = FindUser(user, testUsers)

    }

    
    @Test
    fun findExistingUserByEmail(){

        // change email to an email that is already existing
        findUser!!.user.email = "paul@paul.com"
        val userFound = findUser!!.findUserByEmail()
        val validator = UserValidations(userFound)
        validator.validateBlankFields()

    }

    @Test(expected = BlankFieldException::class)
    fun findNonExistingUserByEmail(){

        // change email to an email that does not yet exist in the system
        val userFound = findUser!!.findUserByEmail()
        val validator = UserValidations(userFound)
        validator.validateBlankFields()

    }

    @Test
    fun findNonExistingUserByNationalId(){

        // change national ID to one already existing
        findUser!!.user.nationalId = 33575433
        val userFound = findUser!!.findUserByNationalId()
        val validator = UserValidations(userFound)
        validator.validateBlankFields()

    }

    @Test(expected = BlankFieldException::class)
    fun findExistingUserByNationalId(){

        val userFound = findUser!!.findUserByNationalId()
        val validator = UserValidations(userFound)
        validator.validateBlankFields()

    }

}