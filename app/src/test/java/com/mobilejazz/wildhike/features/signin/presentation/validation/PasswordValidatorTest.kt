package com.mobilejazz.wildhike.features.signin.presentation.validation

import com.mobilejazz.extension.empty
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PasswordValidatorTest {

    companion object {
        private const val VALID_PASSWORD = "Test1234!"
        private const val INVALID_PASSWORD = "abc123"
    }

    @Test
    fun `test password validator, when valid password  is validated, then result is valid`() {
        val validationResult = PasswordValidator.isValid(VALID_PASSWORD)
        assertTrue(validationResult)
    }

    @Test
    fun `test password validator, when invalid password is validated, then result is invalid`() {
        val validationResult = PasswordValidator.isValid(INVALID_PASSWORD)
        assertFalse(validationResult)
    }

    @Test
    fun `test password validator, when empty password  is validated, then result is invalid`() {
        val validationResult = PasswordValidator.isValid(String.empty)
        assertFalse(validationResult)
    }

    @Test
    fun `test password validator, when no value is validated, then result is invalid`() {
        val validationResult = PasswordValidator.isValid(null)
        assertFalse(validationResult)
    }

}