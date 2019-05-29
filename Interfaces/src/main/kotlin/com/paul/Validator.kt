package com.paul

import com.paul.port.IValidator

class Validator: IValidator {

    override fun validateEmail(email: String): Boolean {
        val email = email
        val emailPattern = Regex(pattern = "" +
                "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
                "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
                "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[" +
                "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\" +
                "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
//        returns true if 'email' is a valid email pattern
        return emailPattern.matches(input = email)
    }

    override fun validatePassword(password: String): Boolean {
        val password = password

        if ( password.length < 8 ) return false
        return true
    }

    override fun validateUsername(username: String): Boolean {

        if ( username.length > 10 ) return false
        return true
    }

    override fun validateAge(age: Int): Boolean {

        val age = age

        if ( age > 100 || age < 0 ) return false
        return true
    }
}