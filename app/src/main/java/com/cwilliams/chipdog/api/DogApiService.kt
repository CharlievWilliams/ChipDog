package com.cwilliams.chipdog.api

import com.cwilliams.chipdog.model.BreedResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {

    @GET("breeds/list/all")
    suspend fun getAllBreeds() : Response<BreedResponse>
}