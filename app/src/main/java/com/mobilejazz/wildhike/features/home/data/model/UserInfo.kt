package com.mobilejazz.wildhike.features.home.data.model

import com.mobilejazz.network.mapper.DataMappedFrom
import com.mobilejazz.wildhike.features.home.data.datasource.remote.UserResponse
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@DataMappedFrom(UserResponse::class)
data class UserInfo(
    val username: String
)
