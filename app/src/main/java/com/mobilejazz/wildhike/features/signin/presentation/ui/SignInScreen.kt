package com.mobilejazz.wildhike.features.signin.presentation.ui

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.mobilejazz.compose.button.PrimaryButton
import com.mobilejazz.compose.input.InputField
import com.mobilejazz.compose.keyboard.KeyboardManager
import com.mobilejazz.compose.keyboard.KeyboardStyle
import com.mobilejazz.compose.layout.CenteredColumn
import com.mobilejazz.compose.layout.DoubleSpacer
import com.mobilejazz.compose.progress.ProgressBar
import com.mobilejazz.compose.text.ErrorText
import com.mobilejazz.compose.text.TitleText
import com.mobilejazz.wildhike.features.signin.presentation.resource.SignInTexts
import com.mobilejazz.wildhike.resources.Images

@Composable
fun SignInScreen(
    isLoading: Boolean,
    emailError: String? = null,
    passwordError: String? = null,
    signInError: String? = null,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInRequest: () -> Unit,
    keyboardManager: KeyboardManager
) {

    CenteredColumn(modifier = Modifier.verticalScroll(rememberScrollState())) {

        Images.WildHikeLogo()

        DoubleSpacer()

        TitleText(
            modifier = Modifier.testTag(SignInTexts.TITLE_TEST_TAG),
            text = SignInTexts.SIGN_IN_LABEL,
        )

        DoubleSpacer()

        InputField(
            label = SignInTexts.YOUR_EMAIL_HINT,
            errorMessage = emailError,
            onTextChanged = { onEmailChange(it) },
            testTag = SignInTexts.EMAIL_INPUT_TEST_TAG
        )

        InputField(
            label = SignInTexts.PASSWORD_HINT,
            errorMessage = passwordError,
            onTextChanged = { onPasswordChange(it) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardStyle = KeyboardStyle.PASSWORD,
            testTag = SignInTexts.PASSWORD_INPUT_TEST_TAG
        )

        PrimaryButton(
            label = SignInTexts.SIGN_IN_LABEL,
            onClick = {
                keyboardManager.hide()
                onSignInRequest()
            },
            icon = Icons.Default.Login,
            testTag = SignInTexts.SIGN_IN_BUTTON_TEST_TAG
        )

        DoubleSpacer()

        if (isLoading) ProgressBar(SignInTexts.PROGRESS_BAR_TEST_TAG)

        signInError?.let {
            ErrorText(
                modifier = Modifier.testTag(SignInTexts.SIGN_IN_ERROR_TEST_TAG),
                text = it,
                textAlign = TextAlign.Center
            )

            DoubleSpacer()
        }

    }
}