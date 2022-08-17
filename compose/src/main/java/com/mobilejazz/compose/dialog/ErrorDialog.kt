package com.mobilejazz.compose.dialog

import androidx.compose.runtime.*
import com.mobilejazz.compose.resources.Colors

@Composable
fun ErrorDialog(
    title: String,
    message: String,
    onClosed: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        BasicAlertDialog(
            title = title,
            description = message,
            imageTint = Colors.red,
            onOkay = {
                showDialog = false
                onClosed.invoke()
            },
        )
    }
}