package com.mobilejazz.wildhike.features.home.data.datasource.remote

import com.mobilejazz.network.call.Resource
import com.mobilejazz.wildhike.features.home.data.model.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeRemoteDataSource {

    @GET("/user-token/{token}")
    suspend fun getUser(@Path("token") token: String?): Resource<UserInfo>
}