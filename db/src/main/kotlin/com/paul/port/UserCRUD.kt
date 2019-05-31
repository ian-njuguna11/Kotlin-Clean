package com.paul.port

import com.paul.entity.UserDataClass
import com.paul.models.UserModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserCRUD {

    constructor(){
        Database.connect(
            "jdbc:postgresql://localhost:5432/kotlin_clean",
            driver = "org.postgresql.Driver",
            user = "postgres", password = "Omwene11@"
        )
    }

    fun createUser(
        user_username: String = "",
        user_email: String = "",
        user_password: String = "",
        user_age: Int = 0 ){

        transaction {
            val id = UserModel.insertAndGetId {
                it[username] = user_username
                it[email] = user_email
                it[password] = user_password
                it[age] = user_age
            }
        }
    }

    fun getAllUsers(): ArrayList<UserDataClass>{
        val users: ArrayList<UserDataClass> = ArrayList()
        transaction {

            val query  = UserModel.selectAll()

            query.forEach{
                users.add( UserDataClass(
                    it[UserModel.username], it[UserModel.email], it[UserModel.password], it[UserModel.age]
                ))
            }
        }

        return users
    }

    fun getSingleUser(user_email: String, user_password: String): UserDataClass{

        var user: UserDataClass = UserDataClass()

        transaction {
            val query = UserModel.select{
                (UserModel.email eq user_email) and (UserModel.password eq user_password)
            }
            query.forEach {
                user.email = it[UserModel.email]
                user.username = it[UserModel.username]

            }
        }

        return user
    }

    fun editUserInfo(
        user_id: Int = 0,
        user_username: String = "",
        user_email: String = "",
        user_password: String = "",
        user_age: Int = 0){

        transaction {
            if (!(user_username.equals(""))) {
                UserModel.update({ UserModel.id eq user_id }) {
                    it[UserModel.username] = user_username
                }
            }

            if (!(user_email.equals(""))) {
                UserModel.update({ UserModel.id eq user_id }) {
                    it[UserModel.email] = user_email
                }
            }

            if (!(user_password.equals(""))) {
                UserModel.update({ UserModel.id eq user_id }) {
                    it[UserModel.password] = user_password
                }
            }

            if (user_age != 0) {
                UserModel.update({ UserModel.id eq user_id }) {
                    it[UserModel.age] = user_age
                }
            }
        }

    }

}