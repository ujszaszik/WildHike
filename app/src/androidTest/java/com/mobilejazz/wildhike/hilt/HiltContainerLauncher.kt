package com.mobilejazz.wildhike.hilt

import android.content.ComponentName
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import com.mobilejazz.wildhike.application.HiltTestActivity

fun startEmptyActivity(themeResId: Int): Intent {
    val key = "androidx.fragment.app.testing.FragmentScenario" +
            ".EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY"
    return Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            HiltTestActivity::class.java
        )
    ).putExtra(key, themeResId)
}