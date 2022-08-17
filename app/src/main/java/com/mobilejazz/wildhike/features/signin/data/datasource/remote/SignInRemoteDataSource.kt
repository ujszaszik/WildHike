package com.mobilejazz.wildhike.features.signin.data.datasource.remote

import com.mobilejazz.network.call.Resource
import com.mobilejazz.wildhike.features.signin.data.model.SignInResult
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInRemoteDataSource {

    @POST("/auth/login")
    suspend fun signIn(@Body request: SignInRequest): Resource<SignInResult>
}