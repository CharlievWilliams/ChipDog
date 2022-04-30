package com.cwilliams.chipdog.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwilliams.chipdog.api.BreedApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val api: BreedApiService
) : ViewModel() {

    val breedList = mutableStateOf<List<String>>(listOf())
    val isRefreshing = mutableStateOf(false)
    val isError = mutableStateOf(false)

    fun refresh() {
        isError.value = false
        isRefreshing.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getAllBreeds()
            if (response.isSuccessful) {
                val list = mutableListOf<String>()
                for (key in response.body()!!.message) {
                    if (key.value.isEmpty()) {
                        // Add generic breed
                        list.add(key.key)
                    } else {
                        // Add each specific breed
                        for (value in key.value) {
                            list.add(value + " " + key.key)
                        }
                    }
                }
                breedList.value = list.toList().sorted()
            } else {
                isError.value = true
            }
            isRefreshing.value = false
        }
    }
}