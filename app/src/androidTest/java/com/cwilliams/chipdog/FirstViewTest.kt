package com.cwilliams.chipdog

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
class FirstViewTest {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun given_when_then() {
        // Given

        // When
        composeTestRule.onNodeWithContentDescription("Dog").performClick()

        // Then
        composeTestRule.onNodeWithContentDescription("Specific dog").assertIsDisplayed()
    }
}