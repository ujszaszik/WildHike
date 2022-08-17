package com.mobilejazz.wildhike.features.signin.data.repository

import com.mobilejazz.network.call.Resource
import com.mobilejazz.wildhike.coroutines.ResourceFlow
import com.mobilejazz.wildhike.features.signin.data.model.SignInResult
import kotlinx.coroutines.flow.flow

class FakeSignInRepository : ISignInRepository {
    private var userToken: String? = null
    var returnError: Boolean = false

    fun setAsSignedIn() {
        userToken = TEST_TOKEN
    }

    override fun isUserLoggedIn(): Boolean {
        return userToken != null
    }

    override fun performSignIn(email: String, password: String): ResourceFlow<SignInResult> {
        return if (returnError) flow { Resource.error<SignInResult>() }
        else flow { Resource.success(SignInResult(TEST_TOKEN)) }
    }

    override fun saveUserToken(token: String) {
        userToken = token
    }

    companion object {
        const val TEST_TOKEN = "Test Token"
    }
}