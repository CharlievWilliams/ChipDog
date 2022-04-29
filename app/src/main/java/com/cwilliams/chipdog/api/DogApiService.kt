package com.cwilliams.chipdog.api

import com.cwilliams.chipdog.model.breedImages.BreedImageResponse
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
    ): Response<BreedImageResponse>

    @GET("breed/{last_name}/{first_name}/images")
    suspend fun getSpecificBreedImages(
        @Path("last_name") lastName: String,
        @Path("first_name") firstName: String
    ): Response<BreedImageResponse>
}