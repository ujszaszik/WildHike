package com.mobilejazz.wildhike.hilt

import android.content.Context
import androidx.multidex.MultiDexApplication
import androidx.work.Configuration
import androidx.work.WorkerFactory
import dagger.hilt.android.internal.testing.TestApplicationComponentManager
import dagger.hilt.android.internal.testing.TestApplicationComponentManagerHolder
import dagger.hilt.internal.GeneratedComponentManager

class HiltAndroidTestApplication : MultiDexApplication(), GeneratedComponentManager<Any>,
    TestApplicationComponentManagerHolder, Configuration.Provider {

    var componentManager: TestApplicationComponentManager? = null

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        componentManager = TestApplicationComponentManager(this)
    }

    override fun componentManager(): GeneratedComponentManager<Any>? {
        return componentManager
    }

    override fun generatedComponent(): Any {
        return componentManager!!.generatedComponent()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(WorkerFactory.getDefaultWorkerFactory())
            .build()
    }
}