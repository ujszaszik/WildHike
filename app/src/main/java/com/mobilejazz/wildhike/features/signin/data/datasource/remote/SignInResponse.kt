package com.mobilejazz.wildhike.features.signin.data.datasource.remote

import com.mobilejazz.network.mapper.DataMapper
import com.mobilejazz.wildhike.features.signin.data.mapper.SignInResultMapper
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@DataMapper(SignInResultMapper::class)
data class SignInResponse(
    val token: String
)