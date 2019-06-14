package com.paul

import com.paul.entity.UserDataClass
import com.paul.port.UserAlreadyExistsException
import com.paul.port.ValidationException
import com.paul.repo.IUserRepo
import java.util.*

class CreateUser(
    var user: UserDataClass = UserDataClass(),
    var userRepo: IUserRepo
) {

    private fun validateInputDetails(){
        when{
            user.email.isEmpty() -> throw ValidationException("Email")
            user.firstName.isEmpty() -> throw ValidationException("First Name")
            user.lastName.isEmpty() -> throw ValidationException("Last Name")
            user.nationalId.isEmpty() -> throw ValidationException("National ID")
            user.password.isEmpty() -> throw ValidationException("Password")
        }
    }

    // checks if user with similar credentials in the system already exists
    private fun findUser(){
        when {
            userRepo.findByNationalId(user.nationalId).isPresent -> throw UserAlreadyExistsException("User with national ID ${user.nationalId} already exists")
            userRepo.findByEmail(user.email).isPresent -> throw UserAlreadyExistsException("User with email ${user.email} already exists")
        }
    }

    fun execute(): Optional<UserDataClass> {
        validateInputDetails()
        findUser()
        return userRepo.create(user)
    }



}