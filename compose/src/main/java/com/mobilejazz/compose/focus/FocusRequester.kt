package com.mobilejazz.compose.focus

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val FOCUS_REQUEST_DELAY = 300L //ms

@SuppressLint("ComposableNaming")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BringIntoViewRequester.requestFocusTo(size: IntSize) {
    val scope = rememberCoroutineScope()
    LaunchedEffect("Request Focus to Element") {
        scope.launch {
            delay(FOCUS_REQUEST_DELAY)
            bringIntoView(Rect(Offset.Zero, size.toSize()))
        }
    }
}