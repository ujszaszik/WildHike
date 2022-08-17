package com.mobilejazz.wildhike.features.signin.data.datasource.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInRequest(
    val username: String,
    val password: String
)