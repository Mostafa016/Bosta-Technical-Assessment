package com.example.bostatechnicalassessment.domain.model

data class CityModel(
    val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val districts: List<DistrictModel>,
    val dropOffAvailability: Boolean,
)