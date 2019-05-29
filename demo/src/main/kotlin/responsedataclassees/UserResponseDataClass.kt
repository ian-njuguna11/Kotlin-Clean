package responsedataclassees

data class UserResponseDataClass(
    val username: String = "",
    val email: String = "",
    val age: Int = 0,
    val password: String = "",
    val error: String = ""
)