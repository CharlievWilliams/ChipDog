package com.cwilliams.chipdog

import androidx.annotation.StringRes
import androidx.navigation.NavBackStackEntry

data class ScreenState(
    val hideBackButton: Boolean = false,
    val showLargeAppBar: Boolean = false,
    var onBackButtonClick: (() -> Unit)? = null
)

sealed class Screen(
    val route: String,
    @StringRes val title: Int,
    val screenState: ScreenState? = ScreenState()
) {
    object BreedListScreen : Screen(
        route = "breed_list_screen",
        title = R.string.list_of_breeds,
        screenState = ScreenState(hideBackButton = true, showLargeAppBar = true)
    )

    object BreedImageScreen : Screen(
        route = "breed_image_screen/{name}",
        title = R.string.dog_images
    )
}

fun toScreen(navBackStackEntry: NavBackStackEntry?): Screen {
    return when (navBackStackEntry?.destination?.route) {
        Screen.BreedListScreen.route -> Screen.BreedListScreen
        Screen.BreedImageScreen.route -> Screen.BreedImageScreen
        else -> Screen.BreedListScreen
    }
}