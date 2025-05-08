package com.example.bostatechnicalassessment.domain.repository

import com.example.bostatechnicalassessment.core.util.Resource
import com.example.bostatechnicalassessment.domain.model.CityModel
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun getAllDistricts(country: Country): Flow<Resource<List<CityModel>>>
    fun searchForCityOrDistrict(query: String): Flow<Resource<List<CityModel>>>
}