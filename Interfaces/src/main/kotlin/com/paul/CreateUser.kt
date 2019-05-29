package com.paul

import com.paul.entity.UserDataClass

class CreateUser(val user: UserDataClass): ICreateUser{

    val validator = Validator()

    override fun validateFields(): String {

        if (!this.validator.validateAge(this.user.age))
            return "Age has to be between 1 and 100 years old"
        else if (!this.validator.validatePassword(this.user.password))
            return "Keep a stronger password"
        else if (!this.validator.validateUsername(this.user.username))
            return "The username cannot be more than 10 characters long"
        else if (!this.validator.validateEmail(this.user.email))
            return "Enter a valid email"
        return ""

    }

    override fun findUser(): Boolean {

        for (singleUser in Users.userList){
            if (singleUser.email == this.user.email)
                return true
        }
        return false

    }

    override fun addUser(): String {

        if (this.validateFields() != "") return this.validateFields()

        else if (this.findUser()) return "User already found"

        Users.userList.add(this.user)

        return "success"
    }


}