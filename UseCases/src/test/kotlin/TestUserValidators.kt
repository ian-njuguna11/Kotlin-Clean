import com.paul.entity.UserDataClass
import com.paul.port.InvalidEmailException
import com.paul.validators.UserValidator
import org.junit.Test


class TestUserValidators{

    @Test
    fun testValidateEmailValid(){

        val user = UserDataClass(
            email="paul@as.com"
        )
        val validateUser = UserValidator(user)
        validateUser.validateEmail()

    }

    @Test(expected = InvalidEmailException::class)
    fun testValidateEmailInvalid(){
        val user = UserDataClass(
            email="paul"
        )
        val validateUser = UserValidator(user)
        validateUser.validateEmail()
    }

}