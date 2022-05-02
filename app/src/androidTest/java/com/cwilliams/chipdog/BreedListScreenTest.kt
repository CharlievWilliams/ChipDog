package com.cwilliams.chipdog

import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.cwilliams.chipdog.ui.theme.ChipDogTheme
import com.cwilliams.chipdog.view.screen.BreedListScreen
import com.cwilliams.chipdog.viewModel.BreedListViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@HiltAndroidTest
class BreedListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun givenListScreen_whenScreenLoad_thenDisplayBreedList() {
        // Given & When
        composeTestRule.setContent {
            ChipDogTheme {
                BreedListScreen(
                    viewModel = composeTestRule.activity.viewModels<BreedListViewModel>().value,
                    navigateToNextScreen = {}
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText("Affenpinscher").assertIsDisplayed()
    }

    @Test
    fun givenListScreen_whenSwipeUp_thenDisplayScrollToTopFab() {
        // Given & When
        composeTestRule.setContent {
            ChipDogTheme {
                BreedListScreen(
                    viewModel = composeTestRule.activity.viewModels<BreedListViewModel>().value,
                    navigateToNextScreen = {}
                )
            }
        }

        // When
        composeTestRule.onNodeWithText("Affenpinscher").performTouchInput { swipeUp() }

        // Then
        composeTestRule.onNodeWithText("Scroll to top").assertIsDisplayed()
    }
}