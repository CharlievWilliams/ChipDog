package com.cwilliams.chipdog

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import okhttp3.internal.wait
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
class BreedListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun given_when_then() {
        // Given
        composeTestRule.waitForIdle()

        // When
        composeTestRule.onNodeWithContentDescription("Button").performTouchInput { swipeUp() }

        // Then
        composeTestRule.onNodeWithText("Scroll to top").assertIsDisplayed()
    }
}