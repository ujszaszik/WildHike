package com.mobilejazz.compose.input

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.IntSize
import com.mobilejazz.compose.focus.requestFocusTo
import com.mobilejazz.compose.keyboard.KeyboardStyle
import com.mobilejazz.compose.resources.Colors
import com.mobilejazz.compose.resources.Dimens
import com.mobilejazz.compose.text.ErrorText
import com.mobilejazz.extension.empty
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    label: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardStyle: KeyboardStyle = KeyboardStyle.DEFAULT,
    testTag: String = String.empty
) {

    var inputText by remember { mutableStateOf(String.empty) }

    var currentSize by remember { mutableStateOf(IntSize.Zero) }

    val relocationRequester = remember { BringIntoViewRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val interactionSourceState = interactionSource.collectIsFocusedAsState()

    if (interactionSourceState.value) relocationRequester.requestFocusTo(currentSize)

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .padding(horizontal = Dimens.paddingDouble)
            .onGloballyPositioned { currentSize = it.size }
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .bringIntoViewRequester(relocationRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        scope.launch { relocationRequester.bringIntoView() }
                    }
                }
                .focusTarget()
                .testTag(testTag),
            value = inputText,
            label = { Text(label) },
            onValueChange = { newValue ->
                inputText = newValue
                onTextChanged.invoke(newValue)
            },
            colors = Colors.inputFieldColors(),
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardStyle.keyboardOptions,
            keyboardActions = keyboardStyle.keyboardActions,
            isError = errorMessage != null,
        ) // TextField

        ErrorText(text = errorMessage.orEmpty())

    } // Column
}