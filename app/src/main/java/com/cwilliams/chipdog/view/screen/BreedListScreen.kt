package com.cwilliams.chipdog.view.screen

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cwilliams.chipdog.R
import com.cwilliams.chipdog.constants.Constants.Companion.ANIMATION_OFFSET
import com.cwilliams.chipdog.view.component.BreedCard
import com.cwilliams.chipdog.view.component.ErrorCard
import com.cwilliams.chipdog.view.component.InitialCard
import com.cwilliams.chipdog.viewModel.BreedListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun BreedListScreen(viewModel: BreedListViewModel, navigateToNextScreen: (String) -> Unit) {

    val breedList = viewModel.breedList.value.groupBy { it[0] }
    val isRefreshing = viewModel.isRefreshing.value
    val isError = viewModel.isError.value

    val listState = rememberLazyListState()
    val isButtonVisible by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current

    LaunchedEffect(viewModel) {
        viewModel.refresh()
    }

    Box {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh() },
            modifier = Modifier.fillMaxSize()
        ) {
            if (isError) {
                Column(
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(dimensionResource(id = R.dimen.default_padding))
                ) {
                    ErrorCard()
                }
            } else {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(dimensionResource(id = R.dimen.default_padding))
                ) {
                    breedList.forEach { (initial, breeds) ->
                        stickyHeader {
                            InitialCard(initial = initial.toString(), isRefreshing = isRefreshing)
                        }
                        items(breeds) { breed ->
                            BreedCard(
                                isRefreshing = isRefreshing,
                                breed = breed,
                                navigateToNextScreen = navigateToNextScreen
                            )
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = isButtonVisible,
                enter = slideInVertically {
                    with(density) { ANIMATION_OFFSET.dp.roundToPx() }
                } + fadeIn(),
                exit = slideOutVertically {
                    with(density) { ANIMATION_OFFSET.dp.roundToPx() }
                } + fadeOut(),
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                ExtendedFloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(index = 0)
                        }
                    },
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.default_padding))
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowUpward,
                        contentDescription = stringResource(id = R.string.scroll_to_top)
                    )
                    Text(text = stringResource(id = R.string.scroll_to_top))
                }
            }
        }
    }
}