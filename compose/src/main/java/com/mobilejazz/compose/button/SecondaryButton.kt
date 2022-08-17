package com.mobilejazz.compose.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.mobilejazz.compose.resources.Colors

@Composable
fun SecondaryButton(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    DefaultButton(
        label = label,
        backgroundColor = Colors.appBlue,
        textColor = Colors.orange,
        icon = icon,
        iconTint = Colors.orange,
        onClick = onClick
    )
}