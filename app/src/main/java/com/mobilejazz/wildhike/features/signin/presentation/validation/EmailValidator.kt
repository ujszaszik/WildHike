package com.mobilejazz.wildhike.features.signin.presentation.validation

import androidx.core.util.PatternsCompat
import com.mobilejazz.wildhike.validation.text.TextValidator

object EmailValidator : TextValidator {

    override val errorMessage: String
        get() = "Please enter a valid email address!"

    override fun isValid(value: String?): Boolean {
        return !value.isNullOrEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(value).matches()
    }
}
