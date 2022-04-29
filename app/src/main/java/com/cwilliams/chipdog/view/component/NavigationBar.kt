package com.cwilliams.chipdog.view.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
            title = {
                Text(
                    text = stringResource(id = screen?.title ?: R.string.list_of_breeds),
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                if (!hideBackButton) {
                    IconButton(onClick = { backButtonClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            },
            scrollBehavior = scrollBehavior
        )
    } else {
        SmallTopAppBar(
            title = {
                Text(
                    text = stringResource(id = screen?.title ?: R.string.list_of_breeds),
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                if (!hideBackButton) {
                    IconButton(onClick = { backButtonClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            }
        )
    }
}