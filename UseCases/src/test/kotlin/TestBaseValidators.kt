import com.paul.port.IntegerException
import com.paul.port.LengthException
import com.paul.port.SpecialCharacterException
import com.paul.validators.BaseValidator
import org.junit.Test


class TestBaseValidators{

    @Test
    fun testMaximumLengthValid(){
        BaseValidator.validateMaximumLength("Paul", 5)
    }

    @Test(expected = LengthException::class)
    fun testMaximumLengthInvalid(){
        BaseValidator.validateMaximumLength("PaulWekesa", 5)
    }

    @Test
    fun testMinimumLengthValid(){
        BaseValidator.validateMinimumLength("PaulWekesa", 5)
    }

    @Test(expected = LengthException::class)
    fun testMinimumLengthInvalid(){
        BaseValidator.validateMinimumLength("Pau", 5)
    }

    @Test(expected = SpecialCharacterException::class)
    fun testSpecialCharacterInStringInvalid(){
        BaseValidator.validateSpecialCharacterInString("this")
    }

    @Test
    fun testSpecialCharacterInStringValid(){
        BaseValidator.validateSpecialCharacterInString("paul@paul.com")
    }

    @Test
    fun testNoSpecialCharacterInStringValid(){
        BaseValidator.validateNoSpecialCharacterInString("this")
    }

    @Test(expected = SpecialCharacterException::class)
    fun testNoSpecialCharacterInStringInvalid(){
        BaseValidator.validateNoSpecialCharacterInString("this@this")
    }

    @Test
    fun testIntegerInStringValid(){
        BaseValidator.validateIntegerInString("this1")
    }

    @Test(expected = IntegerException::class)
    fun testIntegerInStringInvalid(){
        BaseValidator.validateIntegerInString("thisone")
    }

    @Test
    fun testNoIntegerInStringValid(){
        BaseValidator.validateNoIntegerInString("thisone")
    }

    @Test(expected = IntegerException::class)
    fun testNoIntegerInStringInvalid(){
        BaseValidator.validateNoIntegerInString("this1")
    }

}

