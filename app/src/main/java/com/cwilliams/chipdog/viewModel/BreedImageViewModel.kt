package com.cwilliams.chipdog.viewModel

import androidx.lifecycle.ViewModel
import com.cwilliams.chipdog.api.DogApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BreedImageViewModel @Inject constructor(
    private val api: DogApiService
) : ViewModel() {

    fun refresh() {

    }
}