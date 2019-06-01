package com.paul.port

import com.paul.entity.UserDataClass
import com.paul.models.UserModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserCRUD {

    /*
    This class performs CRUD operations on the class table in the database
    NOTE: set DB_USER and DB_PASSWORD fields as the user and password of the Postgresql database you are currently using
     */

    constructor(){
        Database.connect(
            "jdbc:postgresql://localhost:5432/kotlin_clean",
            driver = "org.postgresql.Driver",
            user = System.getenv("DB_USER"), password = System.getenv("DB_PASSWORD")
        )
    }

    fun createUser(
        user_username: String = "",
        user_email: String = "",
        user_password: String = "",
        user_age: Int = 0 ): Boolean{
        /*
        */

        if (userExists(user_email))
            return false

        transaction {
            val id = UserModel.insertAndGetId {
                it[username] = user_username
                it[email] = user_email
                it[password] = HashPassword.createHash(user_password)
                it[age] = user_age
            }
        }

        return true
    }


    fun userExists(user_email: String = ""): Boolean{
        /*
        Find if user email has already been used in the system
        Primarily designed to be used during the signup period but usability might be extended

        Return true if user already exists and false otherwise
         */
        var returnValue = false

        transaction {
            /*
            NOTE: you cannot return while doing a transaction
            Why we created the returnValue that we will use to return either true or false
             */
            val allUsers = getAllUsers()

            for (user in allUsers){
                if (user.email == user_email){
                    returnValue = true
                    return@transaction
                }
            }

        }

        return returnValue
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
        /*
        return a dataclass of a single user from the database , where the email and password match
        Meant to mostly be used during login and user verification
         */
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

            /*
            Gets the information that is to be edited and if the argument in the method is not blank, the field will
            successfully be edited in the database

            user_id will only be used to retrieve the user data and should not be able to be edited in the system
             */

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