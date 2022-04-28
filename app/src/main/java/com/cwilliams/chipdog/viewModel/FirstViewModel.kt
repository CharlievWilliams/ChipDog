package com.cwilliams.chipdog.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwilliams.chipdog.api.DogApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val api: DogApiService
) : ViewModel() {

    val breedList = mutableStateOf(
        listOf(
            "Option 1",
            "Option 2",
            "Option 3",
            "Option 1",
            "Option 2",
            "Option 3",
            "Option 1",
            "Option 2",
            "Option 3",
            "Option 1",
            "Option 2",
            "Option 3"
        )
    )
    val isRefreshing = mutableStateOf(false)

    fun refresh() {
        isRefreshing.value = true
        // TODO: Get working
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getAllBreeds()
            if (response.isSuccessful) {
                // TODO: Save data
                isRefreshing.value = false
            } else {
                // TODO: Error state
                isRefreshing.value = false
            }
        }
    }
}