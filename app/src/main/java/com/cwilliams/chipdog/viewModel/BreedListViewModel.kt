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
class BreedListViewModel @Inject constructor(
    private val api: DogApiService
) : ViewModel() {

    val breedList = mutableStateOf(
        listOf(
            "affenpinscher",
            "african",
            "airedale",
            "akita",
            "appenzeller",
            "australian",
            "basenji",
            "beagle",
            "bluetick",
            "borzoi",
            "bouvier",
            "boxer",
            "brabancon"
        )
    )
    val isRefreshing = mutableStateOf(false)

    fun refresh() {
        isRefreshing.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getAllBreeds()
            if (response.isSuccessful) {
                val breeds = response.body()?.message
                val status = response.body()?.status
                print(breeds)
                print(status)
                // TODO: Save data
            } else {
                // TODO: Error state
            }
            isRefreshing.value = false
        }
    }
}