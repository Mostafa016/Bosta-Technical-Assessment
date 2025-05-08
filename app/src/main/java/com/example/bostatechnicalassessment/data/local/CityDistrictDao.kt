package com.example.bostatechnicalassessment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.bostatechnicalassessment.data.local.entity.CityEntity
import com.example.bostatechnicalassessment.data.local.entity.CityWithDistricts
import com.example.bostatechnicalassessment.data.local.entity.DistrictEntity

@Dao
interface CityDistrictDao {

    //region Insert operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDistrict(district: DistrictEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDistricts(districts: List<DistrictEntity>)
    //endregion

    //region Query operations
    @Transaction
    @Query("SELECT * FROM cities WHERE cityId = :cityId")
    suspend fun getCityWithDistricts(cityId: String): CityWithDistricts?

    @Transaction
    @Query("SELECT * FROM cities")
    suspend fun getAllCitiesWithDistricts(): List<CityWithDistricts>

    @Query("SELECT * FROM districts WHERE cityOwnerId = :cityId")
    suspend fun getDistrictsForCity(cityId: String): List<DistrictEntity>

    @Query("SELECT * FROM cities WHERE cityId = :id")
    suspend fun getCityById(id: String): CityEntity?

    @Query("SELECT * FROM cities WHERE cityId IN (:ids)")
    fun getCitiesByIds(ids: Set<String>): List<CityEntity>
    //endregion

    //region Search
//    @Transaction
//    @Query(
//        """
//    SELECT DISTINCT c.* FROM cities c
//    LEFT JOIN districts d ON c.cityId = d.cityOwnerId
//    WHERE (LOWER(c.cityName) LIKE LOWER(:query) OR LOWER(c.cityOtherName) LIKE LOWER(:query))
//       OR (LOWER(d.districtName) LIKE LOWER(:query) OR LOWER(d.districtOtherName) LIKE LOWER(:query)
//           OR LOWER(d.zoneName) LIKE LOWER(:query) OR LOWER(d.zoneOtherName) LIKE LOWER(:query))
//"""
//    )
//    fun searchCitiesWithDistricts(query: String): List<CityWithDistricts>
    @Query(
        """
        SELECT * FROM cities 
        WHERE LOWER(cityName) LIKE LOWER(:query)
           OR LOWER(cityOtherName) LIKE LOWER(:query) 
        """
    )
    fun getMatchingCities(query: String): List<CityEntity>

    @Query(
        """
        SELECT * FROM districts 
        WHERE LOWER(districtName) LIKE LOWER(:query) 
           OR LOWER(districtOtherName) LIKE LOWER(:query) 
           OR LOWER(zoneName) LIKE LOWER(:query)
           OR LOWER(zoneOtherName) LIKE LOWER(:query)
        """
    )
    fun getMatchingDistricts(query: String): List<DistrictEntity>
    //endregion
}