package com.cwilliams.chipdog.view.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.cwilliams.chipdog.viewModel.BreedImageViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalMaterial3Api
@Composable
fun BreedImageScreen(viewModel: BreedImageViewModel, name: String?) {

    val breedImages = viewModel.breedImages.value
    val isRefreshing = viewModel.isRefreshing.value

    LaunchedEffect(viewModel) {
        viewModel.refresh(name)
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh(name) },
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 180.dp)) {
            items(breedImages) { url ->
                GlideImage(
                    imageModel = url,
                    modifier = Modifier.height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}