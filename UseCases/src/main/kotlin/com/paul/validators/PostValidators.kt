package com.paul.validators

import com.paul.entity.PostDataClass
import com.paul.port.*

class PostValidators(val post: PostDataClass = PostDataClass()) {

    fun validatePostName(){

        // confirm if the name contains special characters
        try{
            BaseValidator.validateSpecialCharacterNotInString( post.name)
            BaseValidator.validateStringDoesNotContainInteger( post.name )
        } catch(e: SpecialCharacterException){
            throw InvalidPostNameException("Special characters ('@', '#', '$'...) cannot be used in the name of a post")
        } catch(e: ContainsIntegerException){
            throw InvalidPostNameException("Name of a post cannot contain a number [0-9]")
        }

    }

    fun validatePostId(){

        try{
            BaseValidator.validateNonNegativeInteger( post.id )
        } catch(e: NegativeIntegerException){
            throw InvalidPostIdException("the ID of a Post should not be negative")
        }

    }

    fun validateAllFields(){

        this.validatePostName()
        this.validatePostId()

    }

}