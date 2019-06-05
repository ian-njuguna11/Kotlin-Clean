package com.paul.UserUseCases

import com.paul.Validators.UserValidations
import com.paul.entity.UserDataClass
import com.paul.port.BlankFieldException

class FindUser(var user: UserDataClass, val userList: ArrayList<UserDataClass> = ArrayList()) {

    fun findUserByEmail(): UserDataClass{
        for (singleUser in userList){
            if (singleUser.email == user.email) return singleUser
        }
        return UserDataClass()
    }

    fun findUserById(): UserDataClass{
        for (singleUser in userList){
            if (singleUser.id == user.id) return singleUser
        }
        return UserDataClass()
    }

    fun findUserByNationslId(): UserDataClass{
        for (singleUser in userList){
            if (singleUser.nationalId == user.nationalId) return singleUser
        }
        return UserDataClass()
    }

    fun findUserByAllUniqueFields(): Boolean{
        // returns true if user is found and false otherwise
        val validator: UserValidations = UserValidations()

        try {
            validator.user = this.findUserByEmail()
            validator.validateBlankFields()
            return true
        } catch(e: BlankFieldException){

        }

        try {
            validator.user = this.findUserById()
            validator.validateBlankFields()
            return true
        } catch(e: BlankFieldException){

        }

        try {
            validator.user = this.findUserByNationslId()
            validator.validateBlankFields()
            return true
        } catch(e: BlankFieldException){

        }

        return false



    }

}
