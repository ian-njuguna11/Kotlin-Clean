package com.paul

import com.paul.entity.UserDataClass

class GetUser: IGetUser {
    override fun getUser(email: String): UserDataClass {

        val users = Users.userList

        for (user in users){
            if (user.email == email){
                return user
            }
        }

        return UserDataClass()

    }

}