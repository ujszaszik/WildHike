package com.mobilejazz.wildhike.validation

interface Validator<Type> {

    fun isValid(value: Type): Boolean
}