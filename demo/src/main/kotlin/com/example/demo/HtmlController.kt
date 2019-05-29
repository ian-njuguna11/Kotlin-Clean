package com.example.demo

import com.paul.entity.UserDataClass
import org.apache.catalina.User
import org.springframework.web.bind.annotation.*
import responsedataclassees.UserResponseDataClass

@RestController
@RequestMapping("/users")
class UserController {

    @PostMapping
    fun postUser(@RequestBody userinfo: UserDataClass): UserResponseDataClass {
        val email: String = userinfo.email

        if ( email == "paul1tw1@gmail.com" )
            return UserResponseDataClass(
                username= "paul1tw1@gmail.com"
            )

        return UserResponseDataClass(
            error = "enter the correct email"
        )

    }

    @GetMapping
    fun getUser(): UserDataClass {

        return UserDataClass(
            email="gmail.com"
        )

    }

}