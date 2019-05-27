package com.paul

//val username: String?, val email: String?, val password: String?, val age: Int?

interface ICreateUser{

    fun validateEmail(): Boolean

    fun validatePassword(): Boolean

    fun validateUsername(): Boolean

    fun validateAge(): Boolean

}