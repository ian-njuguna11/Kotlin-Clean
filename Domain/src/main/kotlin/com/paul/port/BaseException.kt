package com.paul.port

import java.lang.Exception

class ValidationException(message: String): Exception("$message cannot be blank")
class UserAlreadyExistsException(message: String): Exception(message)

// length exception
// thrown when a string is either too long or too short
class LengthException(message: String): Exception(message)
class SpecialCharacterException(message: String): Exception(message)
class IntegerException(message: String): Exception(message)


// user specific exceptions
class InvalidEmailException(message: String): Exception(message)