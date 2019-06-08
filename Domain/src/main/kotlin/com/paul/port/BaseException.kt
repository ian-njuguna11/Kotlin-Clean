package com.paul.port

import java.lang.Exception

class BaseException(message: String): Exception(message)

class MinimumLengthException(message: String): Exception(message)

class MaximumStringLengthException(message: String): Exception(message)

class SpecialCharacterException(message: String): Exception(message)

class ContainsIntegerException(message: String): Exception(message)

class InvalidEmailPatternException(message: String): Exception(message)


// general User exceptions
class InvalidPasswordException(message: String): Exception(message)

class InvalidEmailException(message: String): Exception(message)

class InvalidNationalIdException(message: String): Exception(message)

class InvalidUserNameException(message: String): Exception(message)

class BlankFieldException(message: String): Exception(message)

class UserNotFoundException(message: String): Exception(message)

class UserAlreadyExistsException(message: String): Exception(message)

//general Post Exceptions

class InvalidPostNameException(message: String): Exception(message)

class InvalidPostIdException(message: String): Exception(message)

class NegativeIntegerException(message: String): Exception(message)
