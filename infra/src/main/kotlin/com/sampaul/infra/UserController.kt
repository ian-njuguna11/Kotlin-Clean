package com.sampaul.infra

import com.paul.CreateUser
import com.paul.entity.UserDataClass
import com.paul.port.UserAlreadyExistsException
import com.sampaul.infra.repos.UserRepo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class GreetingController {

    var userRepo = UserRepo()
    @PostMapping(path= ["/create-user"], consumes = ["application/json"], produces = ["application/json"])
    fun createUser(@RequestBody userDataClass: UserDataClass): Map<Long, UserDataClass>{
        val createUser = CreateUser(userDataClass, userRepo)
        var user: Optional<UserDataClass>?= null
        try{
            user = createUser.execute()
        } catch (e: UserAlreadyExistsException){
            return mapOf(0L to UserDataClass(description= e.message!!))
        }
        return mapOf(user!!.get().id to user.get())
    }


}
