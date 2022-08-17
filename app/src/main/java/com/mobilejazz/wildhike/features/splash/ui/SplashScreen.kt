package com.mobilejazz.wildhike.features.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.mobilejazz.compose.layout.CenteredColumn
import com.mobilejazz.compose.resources.Colors
import com.mobilejazz.wildhike.features.splash.resources.SplashTexts
import com.mobilejazz.wildhike.resources.Images

@Composable
fun SplashScreen() {
    CenteredColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag(SplashTexts.SPLASH_SCREEN_TEST_TAG)
            .background(Colors.appBlue),
    ) { Images.WildHikeSplashImage() }
}