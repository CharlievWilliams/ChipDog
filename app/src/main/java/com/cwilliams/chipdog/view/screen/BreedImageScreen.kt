package com.cwilliams.chipdog.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.cwilliams.chipdog.R
import com.cwilliams.chipdog.ui.theme.typography
import com.cwilliams.chipdog.view.component.ErrorCard
import com.cwilliams.chipdog.viewModel.BreedImageViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalMaterial3Api
@Composable
fun BreedImageScreen(viewModel: BreedImageViewModel, name: String?) {

    val breedImages = viewModel.breedImages.value
    val isRefreshing = viewModel.isRefreshing.value
    val isError = viewModel.isError.value

    LaunchedEffect(viewModel) {
        viewModel.refresh(name)
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh(name) },
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = name?.split(" ")?.joinToString(" ") { word ->
                        word.replaceFirstChar { character -> character.titlecase() }
                    } ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(id = R.dimen.default_padding)),
                    style = typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = dimensionResource(id = R.dimen.grid_cell_width)),
                    contentPadding = PaddingValues(dimensionResource(id = R.dimen.default_padding)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.grid_spacing)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.grid_spacing))
                ) {
                    items(breedImages) { url ->
                        GlideImage(
                            imageModel = url,
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen.grid_image_height))
                                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner_size))),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}