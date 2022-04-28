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
    object FirstView : Screen(
        route = "first_view",
        title = R.string.first_view,
        screenState = ScreenState(hideBackButton = true, showLargeAppBar = true)
    )
}

fun toScreen(navBackStackEntry: NavBackStackEntry?): Screen {
    return when (navBackStackEntry?.destination?.route) {
        Screen.FirstView.route -> Screen.FirstView
        else -> Screen.FirstView
    }
}