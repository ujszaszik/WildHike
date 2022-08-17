package com.mobilejazz.compose.resources

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Dimens {

    // PADDING
    val paddingLarge = 32.dp
    val paddingDouble = 16.dp

    private val paddingDefaultHorizontal = 20.dp
    private val paddingDefaultVertical = 12.dp
    val paddingDefaultValues = PaddingValues(
        start = paddingDefaultHorizontal,
        top = paddingDefaultVertical,
        end = paddingDefaultHorizontal,
        bottom = paddingDefaultVertical
    )

    // TEXT
    val smallTextSize = 13.sp
    val defaultTextSize = 15.sp
    val highlightTextSize = 16.sp
    val titleFontSize = 20.sp

    // ICON
    val alertDialogIconSize = 40.dp
}