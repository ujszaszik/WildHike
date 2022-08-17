package com.mobilejazz.compose.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import com.mobilejazz.compose.resources.Dimens
import com.mobilejazz.extension.empty

@Composable
fun DefaultButton(
    label: String,
    backgroundColor: Color,
    textColor: Color,
    icon: ImageVector,
    iconTint: Color,
    testTag: String = String.empty,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.paddingDouble)
            .testTag(testTag),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        onClick = onClick,
        contentPadding = Dimens.paddingDefaultValues
    ) {
        Icon(
            icon,
            tint = iconTint,
            contentDescription = String.empty,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(label, color = textColor)
    }
}