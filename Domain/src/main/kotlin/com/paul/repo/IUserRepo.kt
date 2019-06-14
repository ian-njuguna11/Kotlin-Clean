package com.paul.repo

import com.paul.entity.UserDataClass
import java.util.*

interface IUserRepo: IBaseRepo<Long, UserDataClass>{
    fun findByEmail(email: String): Optional<UserDataClass>
    fun findByNationalId(nationalId: String): Optional<UserDataClass>
    fun findByFirstName(firstName: String): Optional<UserDataClass>
    fun findByLastName(lastName: String): Optional<UserDataClass>
}

