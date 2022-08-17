package com.mobilejazz.wildhike.features.signin.presentation.validation

import com.mobilejazz.wildhike.validation.text.TextValidator
import java.util.regex.Pattern

object PasswordValidator : TextValidator {

    private const val PASSWORD_REGEX =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"

    override val errorMessage: String
        get() = "Please enter a valid password!"


    override fun isValid(value: String?): Boolean {
        return value?.let {
            Pattern.compile(PASSWORD_REGEX).matcher(it).matches()
        } ?: false
    }
}