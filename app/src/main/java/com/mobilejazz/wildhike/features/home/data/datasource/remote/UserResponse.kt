package com.mobilejazz.wildhike.features.home.data.datasource.remote

import com.mobilejazz.network.mapper.DataMapper
import com.mobilejazz.wildhike.features.home.data.mapper.UserResponseMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@DataMapper(UserResponseMapper::class)
data class UserResponse(
    val id: Long,
    val email: String,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    val role: Long
)