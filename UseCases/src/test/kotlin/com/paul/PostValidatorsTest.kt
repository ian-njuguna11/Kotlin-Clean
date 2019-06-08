package com.paul

import com.paul.entity.PostDataClass
import com.paul.port.InvalidPostIdException
import com.paul.port.InvalidPostNameException
import com.paul.validators.PostValidators
import org.junit.Before
import org.junit.Test

class PostValidatorsTest {

    var postToValidate: PostDataClass = PostDataClass()
    var validator: PostValidators = PostValidators()

    @Before
    fun setUp(){
        postToValidate = PostDataClass(1, "MP")
    }

    @Test(expected = InvalidPostNameException::class)
    fun testPostNameWithSpecialCharacter(){
        postToValidate.name = "mp@"
        validator = PostValidators(postToValidate)
        validator.validatePostName()
    }

    @Test(expected = InvalidPostNameException::class)
    fun testPostNameWithInteger(){
        postToValidate.name = "123Name"
        validator = PostValidators(postToValidate)
        validator.validatePostName()
    }

    @Test
    fun testWithValidPostName(){
        validator = PostValidators(postToValidate)
        validator.validatePostName()
    }

    @Test(expected = InvalidPostIdException::class)
    fun testWithNegativePostId(){
        postToValidate.id = -1
        validator = PostValidators(postToValidate)
        validator.validatePostId()
    }

    @Test
    fun testValidId(){
        validator = PostValidators(postToValidate)
        validator.validatePostId()
    }

    @Test
    fun testAllFields(){
        validator = PostValidators(postToValidate)
        validator.validateAllFields()
    }
}