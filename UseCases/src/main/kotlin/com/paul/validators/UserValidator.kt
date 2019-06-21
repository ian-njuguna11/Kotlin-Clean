package com.paul.validators

import com.paul.entity.UserDataClass
import com.paul.port.*
import java.util.regex.Pattern

class UserValidator(
    private val user: UserDataClass
){

    fun validateEmail(){

        val pattern = Regex(
            pattern = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)" +
                "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
                "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
                "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]" +
                "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")

        if (!pattern.matches(input = user.email))
            throw UserValidationException("the email entered is not in the valid format")

    }

    fun validateName(firstOrLast: String){
        var name: String = ""
        var placeHolder: String = ""

        if (firstOrLast == "first"){
            name = user.firstName; placeHolder = "first name"
        } else if (firstOrLast == "last"){
            name = user.lastName; placeHolder = "last name"
        }

        try{
            BaseValidator.validateMinimumLength(name, 2)
            BaseValidator.validateMaximumLength(name, 10)
            BaseValidator.validateNoIntegerInString(name)
            BaseValidator.validateNoSpecialCharacterInString(name)
        } catch(e: LengthException){
            throw UserValidationException("$placeHolder needs to be between 2 and 10 characters in length")
        } catch(e: IntegerException){
            throw UserValidationException("$placeHolder should not contain any numbers")
        } catch(e: SpecialCharacterException){
            throw UserValidationException("$placeHolder should not contain any special characters('@', '#', '$', '%'...)")
        }

    }

    fun validatePassword(){
        try{
            BaseValidator.validateMinimumLength(user.password, 8)
            BaseValidator.validateSpecialCharacterInString(user.password)
            BaseValidator.validateIntegerInString(user.password)
        } catch(e: LengthException){
            throw UserValidationException("password needs to be at least 8 characters long")
        } catch(e: SpecialCharacterException){
            throw UserValidationException("password needs to contain a special character")
        } catch(e: IntegerException){
            throw UserValidationException("password needs to contain a number")
        }
    }

    fun validateCreateUser(){
        validateEmail()
        validatePassword()
        validateName("first")
        validateName("last")
    }

}