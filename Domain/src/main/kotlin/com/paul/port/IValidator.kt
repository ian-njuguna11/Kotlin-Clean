package com.paul.port

interface IValidator {

    fun validateEmail(email: String): Boolean

    fun validatePassword(password: String): Boolean

    fun validateUsername(username: String): Boolean

    fun validateAge(age: Int): Boolean

}
