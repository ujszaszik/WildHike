package com.mobilejazz.wildhike.features.splash.presentation.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.mobilejazz.compose.resources.Colors
import com.mobilejazz.wildhike.BaseComposeAndroidTest
import com.mobilejazz.wildhike.R
import com.mobilejazz.wildhike.extension.assertBackgroundColor
import com.mobilejazz.wildhike.extension.hasDrawable
import com.mobilejazz.wildhike.features.splash.ui.SplashScreen
import com.mobilejazz.wildhike.features.splash.resources.SplashTexts
import com.mobilejazz.wildhike.resources.Images
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class SplashScreenTest : BaseComposeAndroidTest() {

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SplashScreen()
        }
    }

    @Test
    fun testSplashScreen_WhenScreenIsLoaded_ThenLogoIsVisible() {
        composeTestRule
            .onNodeWithTag(Images.WILD_HIKE_SPLASH_IMAGE_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testSplashScreen_WhenScreenIsLoaded_ThenLogoHasAppropriateImage() {
        composeTestRule
            .onNode(hasDrawable(R.drawable.wild_hike_splash))
            .assertIsDisplayed()
    }

    @Test
    fun testSplashScreen_WhenScreenIsLoaded_ThenBackgroundHasAppropriateColor() {
        composeTestRule
            .onNodeWithTag(SplashTexts.SPLASH_SCREEN_TEST_TAG)
            .assertBackgroundColor(Colors.appBlue)
    }

}