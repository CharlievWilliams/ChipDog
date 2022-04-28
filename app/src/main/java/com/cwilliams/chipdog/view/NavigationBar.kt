package com.cwilliams.chipdog.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cwilliams.chipdog.R
import com.cwilliams.chipdog.Screen

@Composable
fun NavigationBar(
    screen: Screen?,
    popBackStack: () -> Unit
) {

    val hideBackButton = screen?.screenState != null && screen.screenState.hideBackButton

    val backButtonClick = {
        screen?.screenState?.onBackButtonClick?.let {
            screen.screenState.onBackButtonClick!!()
        } ?: run { popBackStack() }
    }

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