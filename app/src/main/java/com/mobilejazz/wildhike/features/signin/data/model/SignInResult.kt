package com.mobilejazz.wildhike.features.signin.data.model

import com.mobilejazz.network.mapper.DataMappedFrom
import com.mobilejazz.wildhike.features.signin.data.datasource.remote.SignInResponse

@DataMappedFrom(SignInResponse::class)
data class SignInResult(
    val token: String
)
