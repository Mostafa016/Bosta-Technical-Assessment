package com.example.bostatechnicalassessment.data.remote.dto

data class ApiResponseDto(
    val success: Boolean,
    val message: String,
    val data: List<CityDto>
)
