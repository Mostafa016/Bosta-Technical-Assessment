package com.example.bostatechnicalassessment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val dropOffAvailability: Boolean
)
