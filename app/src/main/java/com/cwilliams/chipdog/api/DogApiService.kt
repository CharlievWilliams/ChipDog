package com.cwilliams.chipdog.api

import com.cwilliams.chipdog.model.breedList.BreedListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): Response<BreedListResponse>

    @GET("breed/{name}/images")
    suspend fun getBreedImages(
        @Path("name") name: String
    ): Response<BreedListResponse>
}