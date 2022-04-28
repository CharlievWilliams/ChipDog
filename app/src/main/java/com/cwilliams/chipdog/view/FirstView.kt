package com.cwilliams.chipdog.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cwilliams.chipdog.viewModel.FirstViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FirstView(viewModel: FirstViewModel, navigateToNextScreen: () -> Unit) {

    val isRefreshing = viewModel.isRefreshing.value
    val breedList = viewModel.breedList.value

    LaunchedEffect(viewModel) {
        viewModel.refresh()
    }

    Column {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh() },
        ) {
            LazyColumn {
                items(breedList) { breed ->
                    Row {
                        Icon(imageVector = Icons.Rounded.Call, contentDescription = null)
                        Text(breed)
                        Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null)
                    }
                }
            }
        }
    }
}