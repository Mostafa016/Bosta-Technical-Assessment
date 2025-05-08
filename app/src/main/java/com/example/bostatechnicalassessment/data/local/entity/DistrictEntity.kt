package com.example.bostatechnicalassessment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "districts")
data class DistrictEntity(
    @PrimaryKey val districtId: String,
    val zoneId: String,
    val zoneName: String,
    val zoneOtherName: String,
    val districtName: String,
    val districtOtherName: String,
    val dropOffAvailability: Boolean,
    val cityOwnerId: String // Foreign key to relate to CityEntity
)

