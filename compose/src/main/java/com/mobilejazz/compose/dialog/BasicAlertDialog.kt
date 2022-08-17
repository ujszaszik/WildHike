package com.mobilejazz.compose.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mobilejazz.compose.button.PrimaryButton
import com.mobilejazz.compose.button.SecondaryButton
import com.mobilejazz.compose.layout.DoubleSpacer
import com.mobilejazz.compose.layout.TopCenterColumn
import com.mobilejazz.compose.resources.Dimens
import com.mobilejazz.compose.resources.Strings
import com.mobilejazz.extension.empty

@Composable
fun BasicAlertDialog(
    title: String = String.empty,
    description: CharSequence? = null,
    imageVector: ImageVector = Icons.Default.Error,
    imageTint: Color,
    isDismissable: Boolean = false,
    onOkay: (() -> Unit)? = null,
    onCancel: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
    body: @Composable (() -> Unit)? = null,
    style: AlertDialogStyle = AlertDialogStyle()
) {
    val totalScreenWidth = LocalConfiguration.current.screenWidthDp

    Dialog(onDismissRequest = { if (isDismissable) onDismiss?.invoke() }) {
        Card(shape = RoundedCornerShape(style.cornerRadius)) {
            TopCenterColumn(
                modifier = Modifier
                    .padding(style.padding)
                    .width((totalScreenWidth * 0.7).dp)
            ) {

                Icon(
                    modifier = Modifier.size(Dimens.alertDialogIconSize),
                    imageVector = imageVector,
                    tint = imageTint,
                    contentDescription = null
                )

                DoubleSpacer()

                if (title.isNotEmpty()) {
                    Text(
                        text = title,
                        fontSize = Dimens.highlightTextSize
                    )
                }

                Spacer(modifier = Modifier.height(style.spacing))

                description?.let {
                    Text(
                        modifier = Modifier.wrapContentSize(Alignment.Center),
                        text = it.toString(),
                        textAlign = TextAlign.Center,
                        fontSize = Dimens.defaultTextSize,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.height(style.spacing))
                }

                body?.invoke()

                onOkay?.let {
                    Column {
                        PrimaryButton(
                            label = Strings.OK,
                            icon = Icons.Filled.Done,
                            onClick = { onOkay() })
                    } // Column
                }

                onCancel?.let {
                    Column {
                        SecondaryButton(
                            label = Strings.CANCEL,
                            icon = Icons.Filled.Cancel,
                            onClick = { onCancel.invoke() }
                        )
                    } // Column
                }

            } // Column
        } // Card
    } // Dialog
}