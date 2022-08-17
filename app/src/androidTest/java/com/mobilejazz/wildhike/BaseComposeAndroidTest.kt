package com.mobilejazz.wildhike

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.mobilejazz.wildhike.application.HiltTestActivity
import com.mobilejazz.wildhike.rules.DisableAnimationsRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule

@Suppress("LeakingThis")
@HiltAndroidTest
open class BaseComposeAndroidTest {

    @get:Rule(order = 0)
    internal var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    internal val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    @get:Rule(order = 2)
    internal val disableAnimationsRule = DisableAnimationsRule()

    @Before
    fun setup() = hiltRule.inject()
}