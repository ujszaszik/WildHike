package com.mobilejazz.wildhike.features.home.presentation.ui

import androidx.compose.ui.test.*
import com.mobilejazz.compose.resources.Colors
import com.mobilejazz.wildhike.BaseComposeAndroidTest
import com.mobilejazz.wildhike.R
import com.mobilejazz.wildhike.extension.assertBackgroundColor
import com.mobilejazz.wildhike.extension.hasDrawable
import com.mobilejazz.wildhike.features.home.presentation.resource.HomeTexts
import com.mobilejazz.wildhike.resources.Images
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest : BaseComposeAndroidTest() {

    companion object {
        private const val TEST_USER_NAME = "TEST NAME"
        private const val TEST_CLICKED = "Clicked"
        private const val TEST_NOT_CLICKED = "Not Clicked"
    }

    private fun setUpScreen(username: String?, onButtonClick: () -> Unit = {}) {
        composeTestRule.setContent {
            HomeScreen(username, onButtonClick)
        }
    }


    // TITLE

    @Test
    fun testHomeScreen_WhenScreenIsLoadedWithUsername_ThenTitleTextIsVisible() {
        setUpScreen(TEST_USER_NAME)
        composeTestRule
            .onNodeWithTag(HomeTexts.TITLE_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testHomeScreen_WhenScreenIsLoadedWithUsername_ThenAppropriateTextIsVisible() {
        setUpScreen(TEST_USER_NAME)
        composeTestRule
            .onNodeWithText(HomeTexts.labelForName(TEST_USER_NAME))
            .assertIsDisplayed()
    }

    @Test
    fun testHomeScreen_WhenScreenIsLoadedWithoutUsername_ThenTitleTextIsNotVisible() {
        setUpScreen(null)
        composeTestRule
            .onNodeWithTag(HomeTexts.TITLE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun testHomeScreen_WhenScreenIsLoadedWithoutUsername_ThenAppropriateTextIsVisible() {
        setUpScreen(null)
        composeTestRule
            .onNodeWithTag(HomeTexts.PROGRESS_BAR_TEST_TAG)
            .assertIsDisplayed()
    }


    // IMAGE

    @Test
    fun testHomeScreen_WhenScreenIsLoaded_ThenImageIsVisible() {
        setUpScreen(TEST_USER_NAME)
        composeTestRule
            .onNodeWithTag(Images.WILD_HIKE_LOGO_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testHomeScreen_WhenScreenIsLoaded_ThenImageHasAppropriateValue() {
        setUpScreen(TEST_USER_NAME)
        composeTestRule
            .onNode(hasDrawable(R.drawable.wild_hike_logo))
            .assertIsDisplayed()
    }


    // BUTTON

    @Test
    fun testHomeScreen_WhenScreenIsLoaded_ThenButtonIsVisible() {
        setUpScreen(TEST_USER_NAME)
        composeTestRule
            .onNodeWithTag(HomeTexts.BUTTON_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testHomeScreen_WhenScreenIsLoaded_ThenButtonIsClickable() {
        setUpScreen(TEST_USER_NAME)
        composeTestRule
            .onNodeWithTag(HomeTexts.BUTTON_TEST_TAG)
            .assertHasClickAction()
    }

    @Test
    fun testHomeScreen_WhenScreenIsLoaded_ThenButtonClickWorksAppropriately() {
        var testValue = TEST_NOT_CLICKED
        setUpScreen(TEST_USER_NAME) { testValue = TEST_CLICKED }
        composeTestRule
            .onNodeWithTag(HomeTexts.BUTTON_TEST_TAG)
            .performClick()

        assert(testValue == TEST_CLICKED)
    }

    @Test
    fun testHomeScreen_WhenScreenIsLoaded_ThenButtonWithAppropriateTextIsVisible() {
        setUpScreen(TEST_USER_NAME)
        composeTestRule
            .onNodeWithText(HomeTexts.SIGN_OUT_LABEL)
            .assertIsDisplayed()
    }

    @Test
    fun testHomeScreen_WhenScreenIsLoaded_ThenButtonBackgroundColorIsAppropriate() {
        setUpScreen(TEST_USER_NAME)
        composeTestRule
            .onNodeWithTag(HomeTexts.BUTTON_TEST_TAG)
            .assertBackgroundColor(Colors.orange)
    }

    // INPUT

    @Test
    fun testHomeScreen_WhenScreenIsLoaded_ThenEmailInputFieldIsShown() {
        setUpScreen(TEST_USER_NAME)
        composeTestRule
            .onNodeWithTag(HomeTexts.BUTTON_TEST_TAG)
            .assertBackgroundColor(Colors.orange)
    }

}