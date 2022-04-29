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
                val list = mutableListOf<String>()
                for (key in response.body()!!.message) {
                    if (key.value.isEmpty()) {
                        list.add(key.key)
                    } else {
                        for (value in key.value) {
                            list.add(value + " " + key.key)
                        }
                    }
                }
                breedList.value = list.toList().sorted()
            } else {
                // TODO: Error state
            }
            isRefreshing.value = false
        }
    }
}