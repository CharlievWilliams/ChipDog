package com.cwilliams.chipdog

import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.cwilliams.chipdog.ui.theme.ChipDogTheme
import com.cwilliams.chipdog.view.screen.BreedImageScreen
import com.cwilliams.chipdog.viewModel.BreedImageViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@HiltAndroidTest
class BreedImageScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun givenValidBreed_whenScreenLoad_thenDisplayBreed() {
        // Given & When
        composeTestRule.setContent {
            ChipDogTheme {
                BreedImageScreen(
                    viewModel = composeTestRule.activity.viewModels<BreedImageViewModel>().value,
                    name = VALID_NAME
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText(VALID_NAME, ignoreCase = true).assertIsDisplayed()
    }

    @Test
    fun givenInvalidBreed_whenScreenLoad_thenDisplayError() {
        // Given & When
        composeTestRule.setContent {
            ChipDogTheme {
                BreedImageScreen(
                    viewModel = composeTestRule.activity.viewModels<BreedImageViewModel>().value,
                    name = INVALID_NAME
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText("An error has occurred. Try refreshing.").assertIsDisplayed()
    }

    companion object {
        const val VALID_NAME = "basenji"
        const val INVALID_NAME = "unknown"
    }
}