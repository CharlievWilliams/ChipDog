package com.cwilliams.chipdog

import com.cwilliams.chipdog.api.BreedApiService
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
    private lateinit var api: BreedApiService

    private lateinit var viewModel: BreedListViewModel

    @Before
    fun init() {
        viewModel = BreedListViewModel(api)
    }

    @Test
    fun givenRefreshingFalse_whenRefreshStarted_thenRefreshingTrue() {
        // Given
        viewModel.isRefreshing.value = false

        // When
        viewModel.refresh()

        // Then
        assertThat(viewModel.isRefreshing.value).isEqualTo(true)
    }

    @Test
    fun givenErrorTrue_whenRefreshStarted_thenErrorFalse() {
        // Given
        viewModel.isError.value = true

        // When
        viewModel.refresh()

        // Then
        assertThat(viewModel.isError.value).isEqualTo(false)
    }
}