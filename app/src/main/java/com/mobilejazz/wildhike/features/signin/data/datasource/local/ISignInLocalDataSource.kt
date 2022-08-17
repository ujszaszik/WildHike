package com.mobilejazz.wildhike.features.signin.data.datasource.local

interface ISignInLocalDataSource {

    fun saveUserToken(token: String)

    fun isLoggedIn(): Boolean
}