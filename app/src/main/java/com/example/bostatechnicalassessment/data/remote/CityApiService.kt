package com.example.bostatechnicalassessment.data.remote

import com.example.bostatechnicalassessment.data.remote.dto.ApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query


interface CityApiService {
    @GET("cities/getAllDistricts")
    suspend fun getAllDistricts(@Query("countryId ") countryId: String): ApiResponseDto

    companion object {
        const val BASE_URL = "https://stg-app.bosta.co/api/v2/"
    }
}

