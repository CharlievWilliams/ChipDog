package com.cwilliams.chipdog.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwilliams.chipdog.api.DogApiService
import com.cwilliams.chipdog.constants.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedImageViewModel @Inject constructor(
    private val api: DogApiService
) : ViewModel() {

    val breedImages = mutableStateOf<List<String>>(listOf())
    val isRefreshing = mutableStateOf(false)

    fun refresh(name: String?) {
        isRefreshing.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getBreedImages(name ?: "")
            if (response.isSuccessful) {
                breedImages.value = response.body()!!.message.asSequence().shuffled().take(Constants.MAX_IMAGES).toList()
                print(breedImages.value)
            } else {
                // TODO: Error state
            }
            isRefreshing.value = false
        }
    }
}