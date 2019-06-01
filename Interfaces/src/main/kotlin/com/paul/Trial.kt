package com.paul

import com.paul.UserInterfaces.CreateUser
import com.paul.entity.UserDataClass



fun main(args: Array<String>){


    val createUser = CreateUser(
        UserDataClass(
            username = "maj", email = "abc@abc.com", password = "password", age = 50
        )
    )
    
    createUser.addUser()

}

