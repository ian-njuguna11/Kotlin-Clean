package com.sampaul.infra.repos

import com.paul.entity.UserDataClass
import com.paul.repo.IUserRepo
import org.springframework.stereotype.Component
import java.util.*
import java.util.Collections.max

@Component
class UserRepo: IUserRepo{

    var userRepoMap = mutableMapOf<Long, UserDataClass>()


    override fun findByEmail(email: String): Optional<UserDataClass> {
        val user = userRepoMap.filter {
            it.value.email == email
        }

        return when{
            user.isEmpty() -> Optional.empty()
            else -> Optional.of(user.values.first())
        }
    }

    override fun findByNationalId(nationalId: String): Optional<UserDataClass> {
        val user = userRepoMap.filter {
            it.value.nationalId == nationalId
        }
        return when{
            user.isEmpty() -> Optional.empty()
            else -> Optional.of(user.values.first())
        }
    }

    override fun findByFirstName(firstName: String): Optional<UserDataClass> {
        val user = userRepoMap.filter {
            it.value.firstName == firstName
        }
        return when {
            user.isEmpty() -> Optional.empty()
            else -> Optional.of(user.values.first())
        }
    }

    override fun findByLastName(lastName: String): Optional<UserDataClass> {
        val user = userRepoMap.filter {
            it.value.lastName == lastName
        }

        return when {
            user.isEmpty() -> Optional.empty()
            else -> Optional.of(user.values.first())
        }
    }

    override fun findById(id: Long): Optional<UserDataClass> {

        val user = userRepoMap.filter{
            it.value.id == id
        }

        return when {
            user.isEmpty() -> Optional.empty()
            else -> Optional.of(user.values.first())
        }
    }

    override fun create(item: UserDataClass): Optional<UserDataClass> {
        val userId = generateId()
        item.id = userId
        userRepoMap[userId] = item
        return Optional.of(item)
    }

    override fun merge(item: UserDataClass): Optional<UserDataClass> {
        userRepoMap[item.id] = item
        return Optional.of(item)
    }

    override fun generateId(): Long {
        return (userRepoMap.size + 1).toLong()
    }

}
