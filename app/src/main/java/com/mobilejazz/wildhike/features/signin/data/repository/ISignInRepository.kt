package com.mobilejazz.wildhike.features.signin.data.repository

import com.mobilejazz.wildhike.coroutines.ResourceFlow
import com.mobilejazz.wildhike.features.signin.data.model.SignInResult

interface ISignInRepository {
    fun performSignIn(email: String, password: String): ResourceFlow<SignInResult>
    fun isUserLoggedIn(): Boolean
    fun saveUserToken(token: String)
}