package com.cwilliams.chipdog.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cwilliams.chipdog.R
import com.cwilliams.chipdog.Screen

@Composable
fun NavigationBar(
    screen: Screen?,
    popBackStack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {

    val hideBackButton = screen?.screenState != null && screen.screenState.hideBackButton
    val showLargeAppBar = screen?.screenState != null && screen.screenState.showLargeAppBar

    val backButtonClick = {
        screen?.screenState?.onBackButtonClick?.let {
            screen.screenState.onBackButtonClick!!()
        } ?: run { popBackStack() }
    }

    if (showLargeAppBar) {
        LargeTopAppBar(
            title = { Text(stringResource(id = screen?.title ?: R.string.first_view)) },
            navigationIcon = {
                if (!hideBackButton) {
                    IconButton(onClick = { backButtonClick() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            },
            scrollBehavior = scrollBehavior
        )
    } else {
        SmallTopAppBar(
            title = { Text(stringResource(id = screen?.title ?: R.string.first_view)) },
            navigationIcon = {
                if (!hideBackButton) {
                    IconButton(onClick = { backButtonClick() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            }
        )
    }
}