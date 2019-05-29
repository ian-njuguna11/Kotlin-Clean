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
        return UserResponseDataClass(
            email =  userinfo.email,
            password = userinfo.password
        )

    }

    @GetMapping
    fun getUser(): UserDataClass {

        return UserDataClass(
            email="gmail.com"
        )

    }

}