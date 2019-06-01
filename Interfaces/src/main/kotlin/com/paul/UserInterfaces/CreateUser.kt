package com.paul.UserInterfaces

import com.paul.ICreateUser
import com.paul.Users
import com.paul.Validator
import com.paul.entity.UserDataClass
import com.paul.port.UserCRUD

class CreateUser(val user: UserDataClass): ICreateUser {

    val validator = Validator()

    override fun validateFields(): String {

        var message = "success"

        if (!this.validator.validateAge(this.user.age))
            message = "Age has to be between 1 and 100 years old"
        else if (!this.validator.validatePassword(this.user.password))
            message = "Keep a stronger password"
        else if (!this.validator.validateUsername(this.user.username))
            message = "The username cannot be more than 10 characters long"
        else if (!this.validator.validateEmail(this.user.email))
            message = "Enter a valid email"
        return message

    }

    override fun findUser(): Boolean {

        for (singleUser in Users.userList){
            if (singleUser.email == this.user.email)
                return true
        }
        return false

    }

    override fun addUser(): String {

        var crud = UserCRUD()
        val createResponse = crud.createUser(
            this.user.username, this.user.email, this.user.password, this.user.age
        )

        if (this.validateFields() != "success")
            return this.validateFields()

        if (!createResponse)
            return "user already exists"

        return "success"
    }

}
