package com.cwilliams.chipdog

import com.cwilliams.chipdog.api.DogApiService
import com.cwilliams.chipdog.viewModel.FirstViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FirstViewModelTest {

    @Mock
    private lateinit var api: DogApiService

    private lateinit var viewModel: FirstViewModel

    @Before
    fun init() {
        viewModel = FirstViewModel(api)
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