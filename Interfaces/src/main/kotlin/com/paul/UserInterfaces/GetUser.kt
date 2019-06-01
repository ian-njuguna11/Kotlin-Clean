package com.paul.UserInterfaces

import com.paul.IGetUser
import com.paul.Users
import com.paul.entity.UserDataClass
import com.paul.port.UserCRUD

class GetUser: IGetUser {

    override fun getUser(email: String, password: String): UserDataClass {
        val userCRUD = UserCRUD()

        return userCRUD.getSingleUser(email, password)
    }

}