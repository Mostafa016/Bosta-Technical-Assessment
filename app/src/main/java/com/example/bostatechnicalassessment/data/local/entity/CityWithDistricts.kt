package com.example.bostatechnicalassessment.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CityWithDistricts(
    @Embedded val city: CityEntity,

    @Relation(
        parentColumn = "cityId",
        entityColumn = "cityOwnerId"
    )
    val districts: List<DistrictEntity>
)

