package com.paul

import com.paul.UserUseCases.EditUser
import com.paul.entity.UserDataClass
import com.paul.port.UserNotFoundException
import org.junit.Before
import org.junit.Test

class EditUserTest {

    val currentUser = UserDataClass(
        1, "paul@paul.com", "Paul", "Wekesa", 33575433, "5trathm0re@"
    )
    var userList: ArrayList<UserDataClass> ?= null
    var editedUser: UserDataClass ?= null
    var editUserObj: EditUser ?= null

    @Before
    fun setUp(){
        userList = ArrayList<UserDataClass>()
        userList!!.add(currentUser)
        editedUser = UserDataClass()
    }

    @Test(expected = UserNotFoundException::class)
    fun editUserWhoDoesNotExist(){
        editedUser!!.id = 2
        editedUser!!.email = "abc@abc.com"
        editedUser!!.firstName = "abc"
        editedUser!!.lastName = "def"
        editedUser!!.password =""
        editedUser!!.nationalId = 243332

        editUserObj = EditUser(editedUser!!, this.userList!!)
        editUserObj!!.run()
    }

}