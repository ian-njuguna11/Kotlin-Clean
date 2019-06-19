package com.paul.validators

import com.paul.port.IntegerException
import com.paul.port.LengthException
import com.paul.port.SpecialCharacterException
import java.util.regex.Pattern


open class BaseValidator {

    companion object {

        // throws an error when string is longer than 'length'
        fun validateMaximumLength(stringToEvaluate: String, length: Int) {
            if (stringToEvaluate.length > length) throw LengthException("The string is too long")
        }

        // throws an error is the string is shorter than 'length'
        fun validateMinimumLength(stringToEvaluate: String, length: Int){
            if (stringToEvaluate.length < length) throw LengthException("The string is too short")
        }

        // throws an error if there is a special character in the string
        fun validateNoSpecialCharacterInString(stringToEvaluate: String){
            val regex: Pattern = Pattern.compile("[\$&+,:;=\\\\\\\\?@#|/'<>.^*()%!-]")

            if (regex.matcher(stringToEvaluate).find())
                throw SpecialCharacterException("string is supposed to contain a special character")
        }

        // throws an error is there is no special character in the string
        fun validateSpecialCharacterInString(stringToEvaluate: String){
            val regex: Pattern = Pattern.compile("[\$&+,:;=\\\\\\\\?@#|/'<>.^*()%!-]")

            if (!regex.matcher(stringToEvaluate).find())
                throw SpecialCharacterException("string is not supposed to contain a special character")
        }

        // throws error when there is an integer in the string
        fun validateNoIntegerInString(stringToEvaluate: String){
            val regex: Pattern = Pattern.compile("[0-9]")
            if (regex.matcher(stringToEvaluate).find()) throw IntegerException("string should not contain an integer")
        }

        // throws an error when there is no integer in the string
        fun validateIntegerInString(stringToEvaluate: String){
            val regex: Pattern = Pattern.compile("[0-9]")
            if (!regex.matcher(stringToEvaluate).find()) throw IntegerException("string should contain an integer")
        }


    }

}