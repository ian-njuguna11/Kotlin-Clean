package com.paul.port

import java.lang.Exception

class ValidationException(message: String): Exception("$message cannot be blank")
class UserAlreadyExistsException(message: String): Exception(message)
