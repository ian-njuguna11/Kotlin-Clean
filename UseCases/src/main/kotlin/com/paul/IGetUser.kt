package com.paul

import com.paul.entity.UserDataClass

interface IGetUser {

    fun getUser(email: String): UserDataClass
}