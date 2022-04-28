package com.cwilliams.chipdog.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cwilliams.chipdog.viewModel.BreedImageViewModel

@ExperimentalMaterial3Api
@Composable
fun BreedImageScreen(viewModel: BreedImageViewModel, name: String?) {

    LaunchedEffect(viewModel) {
        viewModel.refresh()
    }

    Column {

    }
}