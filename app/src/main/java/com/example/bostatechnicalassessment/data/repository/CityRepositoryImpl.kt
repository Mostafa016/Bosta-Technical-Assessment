package com.example.bostatechnicalassessment.data.repository

import android.util.Log
import com.example.bostatechnicalassessment.core.util.Resource
import com.example.bostatechnicalassessment.data.local.CityDatabase
import com.example.bostatechnicalassessment.data.local.entity.CityWithDistricts
import com.example.bostatechnicalassessment.data.local.entity.DistrictEntity
import com.example.bostatechnicalassessment.data.remote.CityApiService
import com.example.bostatechnicalassessment.data.util.DebuggingTags
import com.example.bostatechnicalassessment.data.util.toApiCountry
import com.example.bostatechnicalassessment.data.util.toEntity
import com.example.bostatechnicalassessment.data.util.toModel
import com.example.bostatechnicalassessment.domain.model.CityModel
import com.example.bostatechnicalassessment.domain.repository.CityRepository
import com.example.bostatechnicalassessment.domain.repository.Country
import com.google.gson.JsonParseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityApiService: CityApiService,
    cityDatabase: CityDatabase
) : CityRepository {
    private val cityDao = cityDatabase.cityDao()

    override fun getAllDistricts(country: Country): Flow<Resource<List<CityModel>>> = flow {
        emit(Resource.Loading())

        val localCities = cityDao.getAllCitiesWithDistricts()
            .map { it.toModel() }
        emit(Resource.Loading(data = localCities))
        try {
            val countryId = country.toApiCountry().countryId
            val remoteCities = cityApiService.getAllDistricts(countryId).data.map { it.toModel() }
            emit(Resource.Success(remoteCities))
            updateCityDatabase(remoteCities)
        } catch (e: IOException) {
            emit(Resource.Error(message = "No internet connection."))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Request failed."))
        } catch (e: SocketException) {
            emit(Resource.Error(message = "Connection timed out."))
        } catch (e: JsonParseException) {
            emit(Resource.Error(message = "Response parse error."))
        } catch (e: Exception) {
            emit(Resource.Error(message = "An unexpected error occurred."))
            Log.e(DebuggingTags.CITY_REPOSITORY, e.message, e)
        }
    }

    override fun searchForCityOrDistrict(query: String): Flow<Resource<List<CityModel>>> =
        flow {
            emit(Resource.Loading())

            val trimmedQuery = query.trim()
            if (trimmedQuery.isBlank()) {
                val allCitiesLocal = cityDao.getAllCitiesWithDistricts().map { it.toModel() }
                emit(Resource.Success(data = allCitiesLocal))
            } else {
                val matchingCities = cityDao.getMatchingCities("%$trimmedQuery%")
                val matchingDistricts = cityDao.getMatchingDistricts("%$trimmedQuery%")

                val cityIdToDistricts = matchingDistricts.groupBy { it.cityOwnerId }

                val results = matchingCities.map { city ->
                    CityWithDistricts(
                        city = city,
                        districts = cityIdToDistricts[city.cityId] ?: emptyList()
                    )
                }.toMutableList()

                // Include cities from matching districts only if not already added
                val districtCityIds = matchingDistricts.map { it.cityOwnerId }.toSet()
                val additionalCities =
                    cityDao.getCitiesByIds(districtCityIds.subtract(matchingCities.map { it.cityId }
                        .toSet()))

                results.addAll(additionalCities.map { city ->
                    CityWithDistricts(
                        city = city,
                        districts = cityIdToDistricts[city.cityId] ?: emptyList()
                    )
                })
                emit(Resource.Success(data = results.map { it.toModel() }))
            }
        }


    private suspend fun updateCityDatabase(cityDistricts: List<CityModel>) {
        val updatedCityEntities = cityDistricts.map { it.toEntity() }
        cityDao.insertCities(updatedCityEntities)
        val updatedDistrictEntities: List<DistrictEntity> = cityDistricts.flatMap { cityModel ->
            cityModel.districts.map { it.toEntity(cityModel.cityId) }
        }
        cityDao.insertDistricts(updatedDistrictEntities)
        Log.d(DebuggingTags.CITY_REPOSITORY, "Updated city database successfully")
    }
}