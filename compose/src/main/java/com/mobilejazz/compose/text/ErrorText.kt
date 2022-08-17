package com.mobilejazz.compose.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mobilejazz.compose.resources.Colors
import com.mobilejazz.compose.resources.Dimens

@Composable
fun ErrorText(modifier: Modifier = Modifier, text: String, textAlign: TextAlign = TextAlign.Start) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        color = Colors.red,
        textAlign = textAlign,
        fontSize = Dimens.smallTextSize
    )
}