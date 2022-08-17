package com.mobilejazz.network

enum class ApiErrorCodes(val code: Int, val message: String) {
    DEFAULT(0, "Unexpected error happened during network call."),
    NO_CONNECTION(1, "There is no internet connection."),
    TIMEOUT(2, "The request has timed out."),
    UNAUTHORIZED(30000, "The credentials does not match any user.");
}