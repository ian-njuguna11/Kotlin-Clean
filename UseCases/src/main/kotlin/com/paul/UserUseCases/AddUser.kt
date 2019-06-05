package com.paul.UserUseCases

import com.paul.Validators.UserValidations
import com.paul.entity.UserDataClass
import com.paul.port.UserAlreadyExistsException

class AddUser(private val user: UserDataClass, private val userList: ArrayList<UserDataClass>) {

    fun findUserExists(){
        val findUserObj = FindUser(user, userList)
        if (findUserObj.findUserByAllUniqueFields())
            // find if the user exists by either of the fields
            throw UserAlreadyExistsException("User with those fields already exists in the system")
    }

    fun validateUserFields(){
        val validator = UserValidations(user)
        validator.validateAll() // throws error if any of the fields is not validated correctly
    }

    fun run(){
        findUserExists()
        validateUserFields()
        userList.add(user)
    }

}
