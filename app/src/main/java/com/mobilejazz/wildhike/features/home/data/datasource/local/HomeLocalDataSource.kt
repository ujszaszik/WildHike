package com.mobilejazz.wildhike.features.home.data.datasource.local

import com.mobilejazz.preferences.PreferencesFactory
import com.mobilejazz.preferences.saveString
import com.mobilejazz.wildhike.resources.Texts
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(
    preferencesFactory: PreferencesFactory
) : IHomeLocalDataSource {

    private val safePrefs = preferencesFactory.get()

    override fun getCurrentUserToken(): String? {
        return safePrefs.getString(Texts.USER_TOKEN_KEY, null)
    }

    override fun deleteCurrentUserToken() {
        safePrefs.saveString(Texts.USER_TOKEN_KEY, null)
    }

}