package com.paul.Validators

import com.paul.entity.UserDataClass
import com.paul.port.*

class UserValidations(val user: UserDataClass = UserDataClass()) {

    fun validatePassword() {

        try {
            BaseValidator.validateMinimumStringLength(user.password, 8)
            BaseValidator.validateSpecialCharactersInString(user.password)
            BaseValidator.validateStringContainsInteger(user.password)
        } catch(e: MinimumLengthException){
            throw InvalidPasswordException("The password must be at least 8 characters long")
        } catch(e: SpecialCharacterException){
            throw InvalidPasswordException("The password must contain a special character")
        } catch(e: ContainsIntegerException){
            throw InvalidPasswordException("The password must contain a number")
        }


    }

    fun validateEmail() {

        try{
            BaseValidator.validateEmail(user.email)
        } catch(e: InvalidEmailPatternException){
            throw InvalidEmailException("check your email and try again")
        }

    }

    fun validateUsername(){

    }

}