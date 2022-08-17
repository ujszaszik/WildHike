package com.mobilejazz.wildhike.features.signin.presentation.ui

import androidx.compose.ui.test.*
import com.mobilejazz.compose.keyboard.KeyboardManager
import com.mobilejazz.compose.resources.Colors
import com.mobilejazz.extension.empty
import com.mobilejazz.extension.hideAsPassword
import com.mobilejazz.wildhike.BaseComposeAndroidTest
import com.mobilejazz.wildhike.R
import com.mobilejazz.wildhike.extension.assertBackgroundColor
import com.mobilejazz.wildhike.extension.hasDrawable
import com.mobilejazz.wildhike.features.signin.presentation.resource.SignInTexts
import com.mobilejazz.wildhike.resources.Images
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertTrue
import org.junit.Test

@HiltAndroidTest
class SignInScreenTest : BaseComposeAndroidTest() {

    companion object {
        private const val TEST_EMAIL_INPUT = "test@email.com"
        private const val TEST_EMAIL_ERROR_MESSAGE = "Test Email Error"
        private const val TEST_PASSWORD_INPUT = "abc123"
        private const val TEST_PASSWORD_ERROR_MESSAGE = "Test Password Error"
        private const val TEST_SIGN_IN_CLICK_MESSAGE = "Sign In Clicked"
        private const val TEST_SIGN_IN_ERROR_MESSAGE = "Test Sign In Error"
    }

    private fun setUpScreen(
        isLoading: Boolean = false,
        emailError: String? = null,
        passwordError: String? = null,
        signInError: String? = null,
        onEmailChange: (String) -> Unit = {},
        onPasswordChange: (String) -> Unit = {},
        onSignInRequest: () -> Unit = {},
    ) {
        composeTestRule.setContent {
            SignInScreen(
                isLoading = isLoading,
                emailError = emailError,
                passwordError = passwordError,
                signInError = signInError,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onSignInRequest = onSignInRequest,
                keyboardManager = KeyboardManager(composeTestRule.activity)
            )
        }
    }

    // PROGRESS BAR

    @Test
    fun testSignInScreen_WhenScreenIsLoadedAndLoading_ThenProgressBarIsVisible() {
        setUpScreen(isLoading = true)
        composeTestRule
            .onNodeWithTag(SignInTexts.PROGRESS_BAR_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testSignInScreen_WhenScreenIsLoadedAndNotLoading_ThenProgressBarIsNotVisible() {
        setUpScreen()
        composeTestRule
            .onNodeWithTag(SignInTexts.PROGRESS_BAR_TEST_TAG)
            .assertDoesNotExist()
    }


    // TITLE

    @Test
    fun testSignInScreen_WhenScreenIsLoaded_ThenTitleIsVisible() {
        setUpScreen()
        composeTestRule
            .onNodeWithTag(SignInTexts.TITLE_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testSignInScreen_WhenScreenIsLoaded_ThenImageIsVisible() {
        setUpScreen()
        composeTestRule
            .onNodeWithTag(Images.WILD_HIKE_LOGO_TEST_TAG)
            .assertIsDisplayed()
    }


    // IMAGE

    @Test
    fun testSignInScreen_WhenScreenIsLoaded_ThenImageHasAppropriateValue() {
        setUpScreen()
        composeTestRule
            .onNode(hasDrawable(R.drawable.wild_hike_logo))
            .assertIsDisplayed()
    }


    // FIELD ERROR

    @Test
    fun testSignInScreen_WhenScreenIsLoadedAndHasEmailError_ThenEmailErrorTextIsShown() {
        setUpScreen(emailError = TEST_EMAIL_ERROR_MESSAGE)
        composeTestRule
            .onNodeWithText(TEST_EMAIL_ERROR_MESSAGE)
            .assertIsDisplayed()
    }

    @Test
    fun testSignInScreen_WhenScreenIsLoadedAndHasPasswordError_ThenPasswordErrorTextIsShown() {
        setUpScreen(emailError = TEST_PASSWORD_ERROR_MESSAGE)
        composeTestRule
            .onNodeWithText(TEST_PASSWORD_ERROR_MESSAGE)
            .assertIsDisplayed()
    }

    @Test
    fun testSignInScreen_WhenScreenIsLoadedAndHasSignInError_ThenSignInErrorTextIsShown() {
        setUpScreen(emailError = TEST_SIGN_IN_ERROR_MESSAGE)
        composeTestRule
            .onNodeWithText(TEST_SIGN_IN_ERROR_MESSAGE)
            .assertIsDisplayed()
    }


    // INPUT

    @Test
    fun testSignInScreen_WhenScreenIsLoaded_ThenEmailInputFieldsIsShown() {
        setUpScreen()
        composeTestRule
            .onNodeWithTag(SignInTexts.EMAIL_INPUT_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testSignInScreen_WhenEmailInputEntered_ThenInputFieldUpdatedAppropriately() {
        setUpScreen()
        composeTestRule
            .onNodeWithTag(SignInTexts.EMAIL_INPUT_TEST_TAG)
            .performTextInput(TEST_EMAIL_INPUT)

        composeTestRule
            .onNodeWithText(TEST_EMAIL_INPUT)
            .assertIsDisplayed()
    }

    @Test
    fun testSignInScreen_WhenEmailInputEntered_ThenInputFieldListenerRuns() {
        var emailInput = String.empty
        setUpScreen(onEmailChange = { emailInput = TEST_EMAIL_INPUT })
        composeTestRule
            .onNodeWithTag(SignInTexts.EMAIL_INPUT_TEST_TAG)
            .performTextInput(TEST_EMAIL_INPUT)

        assertTrue(emailInput == TEST_EMAIL_INPUT)
    }

    @Test
    fun testSignInScreen_WhenScreenIsLoaded_ThenPasswordInputFieldsIsShown() {
        setUpScreen()
        composeTestRule
            .onNodeWithTag(SignInTexts.PASSWORD_INPUT_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testSignInScreen_WhenPasswordInputEntered_ThenPasswordIsVisibleHidden() {
        setUpScreen()
        composeTestRule
            .onNodeWithTag(SignInTexts.PASSWORD_INPUT_TEST_TAG)
            .performTextInput(TEST_PASSWORD_INPUT)

        composeTestRule
            .onNodeWithText(TEST_PASSWORD_INPUT.hideAsPassword())
            .assertIsDisplayed()
    }

    @Test
    fun testSignInScreen_WhenPasswordInputEntered_ThenInputFieldListenerRuns() {
        var passwordInput = String.empty
        setUpScreen(onPasswordChange = { passwordInput = TEST_PASSWORD_INPUT })
        composeTestRule
            .onNodeWithTag(SignInTexts.PASSWORD_INPUT_TEST_TAG)
            .performTextInput(TEST_PASSWORD_INPUT)

        assertTrue(passwordInput == TEST_PASSWORD_INPUT)
    }


    // BUTTON

    @Test
    fun testSignInScreen_WhenScreenIsLoaded_ThenButtonIsShown() {
        setUpScreen()
        composeTestRule
            .onNodeWithTag(SignInTexts.SIGN_IN_BUTTON_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testSignInScreen_WhenScreenIsLoaded_ThenButtonBackgroundColorIsAppropriate() {
        setUpScreen()
        composeTestRule
            .onNodeWithTag(SignInTexts.SIGN_IN_BUTTON_TEST_TAG)
            .assertBackgroundColor(Colors.orange)
    }

    @Test
    fun testSignInScreen_WhenScreenIsLoadedAndButtonIsClicked_ThenButtonListenerRunsAppropriately() {
        var testClickMessage = String.empty
        setUpScreen(onSignInRequest = { testClickMessage = TEST_SIGN_IN_CLICK_MESSAGE })
        composeTestRule
            .onNodeWithTag(SignInTexts.SIGN_IN_BUTTON_TEST_TAG)
            .performClick()

        assertTrue(testClickMessage == TEST_SIGN_IN_CLICK_MESSAGE)
    }


    // SIGN IN ERROR

    @Test
    fun testSignInScreen_WhenScreenIsLoadedWithSignInError_ThenAppropriateFieldIsShown() {
        setUpScreen(signInError = TEST_SIGN_IN_ERROR_MESSAGE)
        composeTestRule
            .onNodeWithTag(SignInTexts.SIGN_IN_ERROR_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testSignInScreen_WhenScreenIsLoadedWithSignInError_ThenAppropriateMessageIsShown() {
        setUpScreen(signInError = TEST_SIGN_IN_ERROR_MESSAGE)
        composeTestRule
            .onNodeWithText(TEST_SIGN_IN_ERROR_MESSAGE)
            .assertIsDisplayed()
    }

}