package com.mobilejazz.compose.progress

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.mobilejazz.extension.empty

@Composable
fun ProgressBar(testTag: String = String.empty) {
    CircularProgressIndicator(modifier = Modifier.testTag(testTag))
}