package com.mobilejazz.compose.resources

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Colors {

    val white = Color(0xFFFFFFFF)
    val appBlue = Color(0xFF092C41)
    val orange = Color(0xFFfA8827)
    val red = Color(0xFFFF0000)
    val gray = Color(0xFFC1C1C1)

    @Composable
    fun inputFieldColors() = TextFieldDefaults.textFieldColors(
        textColor = white,
        disabledTextColor = gray,
        focusedLabelColor = white,
        unfocusedLabelColor = gray,
        errorLabelColor = red,
        focusedIndicatorColor = white,
        unfocusedIndicatorColor = gray,
        errorIndicatorColor = red,
        cursorColor = white,
        errorCursorColor = red
    )
}