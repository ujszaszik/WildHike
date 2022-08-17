package com.mobilejazz.wildhike.application

import com.jakewharton.threetenabp.AndroidThreeTen
import com.mobilejazz.network.NetworkUtil
import com.mobilejazz.wildhike.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class WildHikeApplication : android.app.Application() {

    @Inject
    lateinit var networkUtil: NetworkUtil

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)
        networkUtil.registerNetworkCallback()
    }
}