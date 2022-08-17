package com.mobilejazz.extension

val String.Companion.empty: String
    get() = ""

val String.Companion.space: String
    get() = " "

fun String.hideAsPassword(): String {
    return toCharArray()
        .map { Char.bullet }
        .joinToString(String.empty)
}