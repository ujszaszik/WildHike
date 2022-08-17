package com.mobilejazz.wildhike.validation.text

import com.mobilejazz.extension.empty
import com.mobilejazz.wildhike.validation.Validator

interface TextValidator : Validator<String?> {

    val errorMessage: String
        get() = String.empty
}