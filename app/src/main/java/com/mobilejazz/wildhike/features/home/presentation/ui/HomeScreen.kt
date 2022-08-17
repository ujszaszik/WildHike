package com.mobilejazz.wildhike.features.home.presentation.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.mobilejazz.compose.button.PrimaryButton
import com.mobilejazz.compose.layout.DoubleSpacer
import com.mobilejazz.compose.layout.LargeSpacer
import com.mobilejazz.compose.layout.TopCenterColumn
import com.mobilejazz.compose.progress.ProgressBar
import com.mobilejazz.compose.text.TitleText
import com.mobilejazz.wildhike.features.home.presentation.resource.HomeTexts
import com.mobilejazz.wildhike.resources.Images

@Composable
fun HomeScreen(
    username: String?,
    onSignOutRequest: () -> Unit
) {

    TopCenterColumn {

        LargeSpacer()

        Images.WildHikeLogo()

        if (username != null) {

            DoubleSpacer()

            TitleText(
                modifier = Modifier.testTag(HomeTexts.TITLE_TEST_TAG),
                text = HomeTexts.labelForName(username)
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                label = HomeTexts.SIGN_OUT_LABEL,
                onClick = { onSignOutRequest() },
                icon = Icons.Default.Logout,
                testTag = HomeTexts.BUTTON_TEST_TAG
            )

            DoubleSpacer()

        } else {
            TopCenterColumn(Modifier.fillMaxWidth()) {
                DoubleSpacer()
                ProgressBar(HomeTexts.PROGRESS_BAR_TEST_TAG)
                DoubleSpacer()
            }
        }

    }
}