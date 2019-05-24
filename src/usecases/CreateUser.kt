package usecases
import domain.User

//val username: String?, val email: String?, val password: String?, val age: Int?

interface CreateUser{

    // validates the email and username if they meet the system requirements
    fun validateFields(user: User)

    // checks in the storage medium being used a user with the email and password already exists
    fun checkUserExists(user: User)

    // checks for the strengths of the password based on the requirements in the system
    fun validatePassword(user: User)

    // adds the user into the storage unit being used (List, Database, Array etc)
    fun insertUser(user: User)

}