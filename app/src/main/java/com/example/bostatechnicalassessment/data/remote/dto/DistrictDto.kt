package com.example.bostatechnicalassessment.data.remote.dto

data class DistrictDto(
    val zoneId: String,
    val zoneName: String,
    val zoneOtherName: String,
    val districtId: String,
    val districtName: String,
    val districtOtherName: String,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
)