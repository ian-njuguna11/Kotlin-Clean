package com.paul.Validators

import com.paul.port.*

open class BaseValidator {

    companion object {


        fun validateMinimumStringLength(stringToValidate: String, minLength: Int) {
            val maxLengthPattern = "^.{$minLength,}$".toRegex()
            if (!maxLengthPattern.matches(stringToValidate))
                throw MinimumLengthException("String is too short")
        }

        fun validateMaximumStringLength(stringToValidate: String, maxLength: Int) {
            val maxLengthPattern = "^.{1, $maxLength}$".toRegex()
            if (!maxLengthPattern.matches(stringToValidate))
                throw MaximumStringLengthException("String is too long")
        }

        // checks if special characters are in a string
        // special cahracters: @, #, $, %...
        fun validateSpecialCharactersInString(stringToValidate: String) {
            val specialCharacterPattern = ".*[\$&+,:;=?@#|].*".toRegex()
            if (!specialCharacterPattern.containsMatchIn(stringToValidate))
                throw SpecialCharacterException("no special characters in the string")
        }

        fun validateSpecialCharacterNotInString(stringToValidate: String){

            val specialCharacterPattern = ".*[\$&+,:;=?@#|].*".toRegex()
            if (specialCharacterPattern.containsMatchIn(stringToValidate))
                throw SpecialCharacterException("special characters in the string")
        }

        fun validateEmail(stringToValidate: String) {
            val emailPattern = Regex(
                pattern = "" +
                        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"" +
                        "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
                        "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
                        "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[" +
                        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                        "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                        "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\" +
                        "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
            )
            if (!emailPattern.matches(input = stringToValidate))
                throw InvalidEmailPatternException("pattern entered does not match one of an email")
        }

        fun validateStringContainsInteger(stringToValidate: String) {
            val containsIntegerPattern = ".*[0-9].*".toRegex()
            if (!containsIntegerPattern.containsMatchIn(stringToValidate))
                throw ContainsIntegerException("Integer not present")
        }

        fun validateStringDoesNotontainInteger(stringToValidate: String) {
            val containsIntegerPattern = ".*[0-9].*".toRegex()
            if (containsIntegerPattern.containsMatchIn(stringToValidate))
                throw ContainsIntegerException("Integer is present")
        }

    }

}