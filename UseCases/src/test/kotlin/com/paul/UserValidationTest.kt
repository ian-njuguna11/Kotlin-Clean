package com.paul

import com.paul.validators.UserValidations
import com.paul.entity.UserDataClass
import com.paul.port.*
import kotlin.test.Test
import kotlin.test.BeforeTest
//import org.junit.Test

class UserValidationTest {

    var user: UserDataClass = UserDataClass()


    @BeforeTest
    fun setUp(){
        user = UserDataClass(
           email="abc@abc.com", firstName = "Paul", lastName = "Wekesa", nationalId = 23525633, password = "Bluesky@123"
        )
    }

    @Test(expected = InvalidPasswordException::class)
    fun testWithTooShortPassword(){
        user.password = "1234"
        val userVal = UserValidations(user)
        userVal.validatePassword()

    }

    @Test(expected = InvalidPasswordException::class)
    fun testPasswordWithoutSpecialCharacters(){

        user.password = "thispassword"
        val userVal = UserValidations(user)
        userVal.validatePassword()

    }

    @Test(expected = InvalidPasswordException::class)
    fun testPasswordWithoutNumber(){

        user.password = "thispassword@"
        val userVal = UserValidations(user)
        userVal.validatePassword()

    }

    @Test
    fun testValidPassword(){

        val userVal = UserValidations(user)
        userVal.validatePassword()

    }

    @Test(expected = InvalidEmailException::class)
    fun testInvalidEmail(){

        user.email = "google.com"
        val userVal = UserValidations(user)
        userVal.validateEmail()

    }

    @Test(expected = InvalidNationalIdException::class)
    fun testInvalidNationalId(){

        user.nationalId = -1
        val userVal = UserValidations(user)
        userVal.validateNationalId()

    }

    @Test(expected = InvalidUserNameException::class)
    fun testInvalidUserName(){

        user.firstName = "paul@"
        user.lastName = "wekesa32"
        val userVal = UserValidations(user)
        userVal.validateName()

    }

    @Test(expected = BlankFieldException::class)
    fun testSingleBlankField(){

        user.email = ""
        val userVal = UserValidations(user)
        userVal.validateBlankFields()

    }





}