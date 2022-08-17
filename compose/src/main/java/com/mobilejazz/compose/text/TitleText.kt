package com.mobilejazz.compose.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.mobilejazz.compose.resources.Colors
import com.mobilejazz.compose.resources.Dimens

@Composable
fun TitleText(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        color = Colors.white,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.titleFontSize
    )
}