package com.example.bostatechnicalassessment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bostatechnicalassessment.data.local.entity.CityEntity
import com.example.bostatechnicalassessment.data.local.entity.DistrictEntity

@Database(
    entities = [CityEntity::class, DistrictEntity::class],
    version = 1
)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDistrictDao

    companion object {
        const val DATABASE_NAME = "city_database"
    }
}