package com.paul.validators

import com.paul.entity.UserDataClass
import com.paul.port.*

class UserValidations(var user: UserDataClass = UserDataClass()) {

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

    fun validateNationalId(){

        if (user.nationalId < 0) throw InvalidNationalIdException("Too low to be a national ID")

        else if (user.nationalId > 10000000) throw InvalidNationalIdException("Too high to be a national ID")

    }

    fun validateName(){

        // validate the first name
        val userNames = listOf(user.firstName, user.lastName)

        for (name in userNames) {
            try {
                BaseValidator.validateSpecialCharacterNotInString(name)
                BaseValidator.validateStringDoesNotontainInteger(name)
            } catch (e: ContainsIntegerException) {
                throw InvalidUserNameException("name cannot contain special characters")
            } catch (e: SpecialCharacterException) {
                throw InvalidUserNameException("name cannot contain special characters")
            }
        }

    }

    fun validateBlankFields(){

        if ( user.nationalId == 0 || user.email == "" || user.firstName == "" || user.lastName == "" || user.password == ""){
            throw BlankFieldException("none of the user fields can be left blank")
        }

    }



    fun validateAll(){

        this.validateName()
        this.validateEmail()
        this.validateNationalId()
        this.validatePassword()
        this.validateBlankFields()

    }


}