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

    val breedList = mutableStateOf<List<String>>(listOf())
    val isRefreshing = mutableStateOf(false)

    fun refresh() {
        isRefreshing.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getAllBreeds()
            if (response.isSuccessful) {
                // TODO: Get more detailed Data
                breedList.value = response.body()!!.message.getData()
            } else {
                // TODO: Error state
            }
            isRefreshing.value = false
        }
    }
}