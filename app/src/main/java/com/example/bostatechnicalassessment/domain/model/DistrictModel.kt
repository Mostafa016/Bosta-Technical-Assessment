package com.example.bostatechnicalassessment.domain.model

data class DistrictModel(
    val zoneId: String,
    val zoneName: String,
    val zoneOtherName: String,
    val districtId: String,
    val districtName: String,
    val districtOtherName: String,
    val dropOffAvailability: Boolean,
)