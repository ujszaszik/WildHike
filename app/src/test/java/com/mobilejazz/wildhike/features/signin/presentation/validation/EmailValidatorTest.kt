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
class EmailValidatorTest {

    companion object {
        private const val VALID_EMAIL = "valid.email@address.com"
        private const val INVALID_EMAIL = "INVALID EMAIL ADDRESS"
    }

    @Test
    fun `test email validator, when valid email address is validated, then result is valid`() {
        val validationResult = EmailValidator.isValid(VALID_EMAIL)
        assertTrue(validationResult)
    }

    @Test
    fun `test email validator, when invalid email address is validated, then result is invalid`() {
        val validationResult = EmailValidator.isValid(INVALID_EMAIL)
        assertFalse(validationResult)
    }

    @Test
    fun `test email validator, when empty email address is validated, then result is invalid`() {
        val validationResult = EmailValidator.isValid(String.empty)
        assertFalse(validationResult)
    }

    @Test
    fun `test email validator, when no value is validated, then result is invalid`() {
        val validationResult = EmailValidator.isValid(null)
        assertFalse(validationResult)
    }

}