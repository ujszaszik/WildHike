package com.mobilejazz.wildhike.features.signin.data.datasource.local

import com.mobilejazz.preferences.PreferencesFactory
import com.mobilejazz.preferences.saveString
import com.mobilejazz.wildhike.resources.Texts
import javax.inject.Inject

class SignInLocalDataSource @Inject constructor(
    preferencesFactory: PreferencesFactory
) : ISignInLocalDataSource {

    private val safePrefs = preferencesFactory.get()

    override fun saveUserToken(token: String) {
        safePrefs.saveString(Texts.USER_TOKEN_KEY, token)
    }

    override fun isLoggedIn(): Boolean {
        return !(safePrefs.getString(Texts.USER_TOKEN_KEY, null).isNullOrEmpty())
    }
}