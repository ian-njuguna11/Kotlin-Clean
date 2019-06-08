package com.paul.UserUseCases

import com.paul.validators.UserValidations
import com.paul.entity.UserDataClass
import com.paul.port.BlankFieldException
import com.paul.port.UserNotFoundException

class EditUser(val user: UserDataClass, val userList: ArrayList<UserDataClass>) {

    private fun findIfUserExists(){
        val findUser = FindUser(user, userList)
        try{
            val validator = UserValidations(findUser.findUserById())
            validator.validateBlankFields()
        } catch(e: BlankFieldException){
            throw UserNotFoundException("The user with that ID has not been found")
        }
    }

    private fun validateUserFields(){

        val validator = UserValidations(user)
        validator.validateAll() // throws the appropriate error if the fields are not validated

    }

    fun run(){

        findIfUserExists() // throws an error if the user does not exist
        validateUserFields() // throws erorr if any of the fields is invalid
        for (singleUser in userList){
            if (singleUser.id == user.id){
                singleUser.email = user.email
                singleUser.nationalId = user.nationalId
                singleUser.firstName = user.firstName
                singleUser.lastName = user.lastName
                singleUser.password = user.password
            }
        }

    }

}