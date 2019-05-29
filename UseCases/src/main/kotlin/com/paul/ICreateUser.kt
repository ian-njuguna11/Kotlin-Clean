package com.paul

//val username: String?, val email: String?, val password: String?, val age: Int?

interface ICreateUser{

    fun validateFields(): String

    fun findUser(): Boolean

    fun addUser(): String

}