package com.cwilliams.chipdog.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwilliams.chipdog.api.BreedApiService
import com.cwilliams.chipdog.constants.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedImageViewModel @Inject constructor(
    private val api: BreedApiService
) : ViewModel() {

    val breedImages = mutableStateOf<List<String>>(listOf())
    val isRefreshing = mutableStateOf(false)
    val isError = mutableStateOf(false)

    fun refresh(name: String?) {
        isError.value = false
        isRefreshing.value = true
        viewModelScope.launch(Dispatchers.IO) {
            // Perform different request if breed is specific or generic
            val response = if (name?.contains(" ") == true) {
                val list = name.split(" ")
                api.getSpecificBreedImages(firstName = list.first(), lastName = list.last())
            } else {
                api.getBreedImages(name = name ?: "")
            }
            if (response.isSuccessful) {
                // Retrieve 10 random images (up to if fewer are available)
                breedImages.value =
                    response.body()!!.message.asSequence().shuffled().take(Constants.MAX_IMAGES)
                        .toList()
            } else {
                isError.value = true
            }
            isRefreshing.value = false
        }
    }
}