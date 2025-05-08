package com.example.bostatechnicalassessment.data.remote.dto

data class CityDto(
    val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val cityCode: String,
    val districts: List<DistrictDto>,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
)