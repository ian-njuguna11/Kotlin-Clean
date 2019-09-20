package com.paul

class AlreadyExistsException(message: String): Exception(message)
class DoesNotExistException(message: String): Exception(message)
class ValidationException(message: String): Exception(message)