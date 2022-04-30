package com.cwilliams.chipdog

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.cwilliams.chipdog.ui.theme.ChipDogTheme
import com.cwilliams.chipdog.view.screen.BreedListScreen
import com.cwilliams.chipdog.viewModel.BreedListViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@HiltAndroidTest
class BreedListScreenTest {

    @get:Rule(order = 0)
    val composeTestRule = createComposeRule()

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var viewModel: BreedListViewModel

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun given_when_then() {
        // Given
        composeTestRule.setContent {
            ChipDogTheme {
                BreedListScreen(viewModel = viewModel, navigateToNextScreen = {})
            }
        }
        composeTestRule.waitForIdle()

        // When
        composeTestRule.onNodeWithContentDescription("Button").performTouchInput { swipeUp() }

        // Then
        composeTestRule.onNodeWithText("Scroll to top").assertIsDisplayed()
    }
}