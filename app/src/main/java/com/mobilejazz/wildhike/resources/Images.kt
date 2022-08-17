package com.mobilejazz.wildhike.resources

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import com.mobilejazz.compose.semantics.drawableId
import com.mobilejazz.wildhike.R
import com.mobilejazz.wildhike.features.splash.resources.SplashTexts

object Images {

    const val WILD_HIKE_SPLASH_IMAGE_TEST_TAG = "Test::WildHikeSplashImage"

    @Composable
    fun WildHikeSplashImage() {
        val drawableResId = R.drawable.wild_hike_splash
        Image(
            modifier = Modifier
                .size(Dimens.splashLogoSize)
                .semantics { drawableId = drawableResId }
                .testTag(WILD_HIKE_SPLASH_IMAGE_TEST_TAG),
            painter = painterResource(drawableResId),
            contentDescription = SplashTexts.APP_TITLE
        )
    }

    const val WILD_HIKE_LOGO_TEST_TAG = "Test::WildHikeLogo"

    @Composable
    fun WildHikeLogo() {
        val drawableResId = R.drawable.wild_hike_logo
        Image(
            modifier = Modifier
                .size(Dimens.mainLogoSize)
                .semantics { drawableId = drawableResId }
                .testTag(WILD_HIKE_LOGO_TEST_TAG),
            painter = painterResource(drawableResId),
            contentDescription = SplashTexts.APP_TITLE
        )
    }
}