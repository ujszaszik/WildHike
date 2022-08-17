package com.mobilejazz.compose.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Approval
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.*
import com.mobilejazz.compose.resources.Colors

@Composable
fun SelectorDialog(
    title: String,
    message: String,
    onOkay: () -> Unit,
    onCancel: () -> Unit,
    onClosed: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        BasicAlertDialog(
            title = title,
            description = message,
            imageVector = Icons.Default.QuestionAnswer,
            imageTint = Colors.appBlue,
            onOkay = {
                showDialog = false
                onOkay()
                onClosed()
            },
            onCancel = {
                showDialog = false
                onCancel()
                onClosed()
            },
            onDismiss = { onClosed() }
        )
    }
}