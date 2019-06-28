import com.paul.port.IntegerException
import com.paul.port.LengthException
import com.paul.port.SpecialCharacterException
import com.paul.validators.BaseValidator
import org.junit.Test


class BaseValidatorsTest{

    @Test
    fun maximumLengthValidTest(){
        BaseValidator.validateMaximumLength("Paul", 5)
    }

    @Test(expected = LengthException::class)
    fun maximumLengthInvalidTest(){
        BaseValidator.validateMaximumLength("PaulWekesa", 5)
    }

    @Test
    fun minimumLengthValidTest(){
        BaseValidator.validateMinimumLength("PaulWekesa", 5)
    }

    @Test(expected = LengthException::class)
    fun minimumLengthInvalidTest(){
        BaseValidator.validateMinimumLength("Pau", 5)
    }

    @Test(expected = SpecialCharacterException::class)
    fun specialCharacterInStringInvalidTest(){
        BaseValidator.validateSpecialCharacterInString("this")
    }

    @Test
    fun specialCharacterInStringValidTest(){
        BaseValidator.validateSpecialCharacterInString("paul@paul.com")
    }

    @Test
    fun noSpecialCharacterInStringValidTest(){
        BaseValidator.validateNoSpecialCharacterInString("this")
    }

    @Test(expected = SpecialCharacterException::class)
    fun noSpecialCharacterInStringInvalidTest(){
        BaseValidator.validateNoSpecialCharacterInString("this@this")
    }

    @Test
    fun integerInStringValidTest(){
        BaseValidator.validateIntegerInString("this1")
    }

    @Test(expected = IntegerException::class)
    fun integerInStringInvalidTest(){
        BaseValidator.validateIntegerInString("thisone")
    }

    @Test
    fun testNoIntegerInStringValid(){
        BaseValidator.validateNoIntegerInString("thisone")
    }

    @Test(expected = IntegerException::class)
    fun noIntegerInStringInvalidTest(){
        BaseValidator.validateNoIntegerInString("this1")
    }

}

