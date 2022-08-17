package com.mobilejazz.compose.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.mobilejazz.compose.resources.Colors
import com.mobilejazz.extension.empty

@Composable
fun PrimaryButton(
    label: String,
    icon: ImageVector,
    testTag: String = String.empty,
    onClick: () -> Unit
) {
    DefaultButton(
        label = label,
        backgroundColor = Colors.orange,
        textColor = Colors.white,
        icon = icon,
        iconTint = Colors.white,
        testTag = testTag,
        onClick = onClick
    )
}