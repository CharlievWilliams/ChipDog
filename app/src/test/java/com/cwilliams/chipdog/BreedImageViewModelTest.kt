package com.cwilliams.chipdog

import com.cwilliams.chipdog.api.BreedApiService
import com.cwilliams.chipdog.viewModel.BreedImageViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BreedImageViewModelTest {

    @Mock
    private lateinit var api: BreedApiService

    private lateinit var viewModel: BreedImageViewModel

    @Before
    fun init() {
        viewModel = BreedImageViewModel(api)
    }

    @Test
    fun givenRefreshingFalse_whenRefreshStarted_thenRefreshingTrue() {
        // Given
        viewModel.isRefreshing.value = false

        // When
        viewModel.refresh(NAME)

        // Then
        assertThat(viewModel.isRefreshing.value).isEqualTo(true)
    }

    @Test
    fun givenErrorTrue_whenRefreshStarted_thenErrorFalse() {
        // Given
        viewModel.isError.value = true

        // When
        viewModel.refresh(NAME)

        // Then
        assertThat(viewModel.isError.value).isEqualTo(false)
    }

    companion object {
        const val NAME = "Basenji"
    }
}