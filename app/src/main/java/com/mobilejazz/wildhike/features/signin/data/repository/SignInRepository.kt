package com.mobilejazz.wildhike.features.signin.data.repository

import com.mobilejazz.network.operation.networkFlow
import com.mobilejazz.wildhike.coroutines.ResourceFlow
import com.mobilejazz.wildhike.features.signin.data.datasource.local.SignInLocalDataSource
import com.mobilejazz.wildhike.features.signin.data.datasource.remote.SignInRemoteDataSource
import com.mobilejazz.wildhike.features.signin.data.datasource.remote.SignInRequest
import com.mobilejazz.wildhike.features.signin.data.model.SignInResult
import javax.inject.Inject

class SignInRepository @Inject constructor(
    private val localDataSource: SignInLocalDataSource,
    private val remoteDataSource: SignInRemoteDataSource
) : ISignInRepository {

    override fun performSignIn(email: String, password: String): ResourceFlow<SignInResult> {
        return networkFlow {
            val request = SignInRequest(email, password)
            remoteDataSource.signIn(request)
        }
    }

    override fun isUserLoggedIn() = localDataSource.isLoggedIn()

    override fun saveUserToken(token: String) = localDataSource.saveUserToken(token)
}