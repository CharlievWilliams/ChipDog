package com.cwilliams.chipdog

import com.cwilliams.chipdog.api.DogApiService
import com.cwilliams.chipdog.viewModel.BreedListViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BreedListViewModelTest {

    @Mock
    private lateinit var api: DogApiService

    private lateinit var viewModel: BreedListViewModel

    @Before
    fun init() {
        viewModel = BreedListViewModel(api)
    }

    @Test
    fun given_when_then() {
        // Given

        // When
        viewModel.refresh()

        // Then
        assertThat(viewModel.breedList).isNotNull()
    }
}