package com.example.bostatechnicalassessment.data.util

import com.example.bostatechnicalassessment.data.local.entity.CityEntity
import com.example.bostatechnicalassessment.data.local.entity.CityWithDistricts
import com.example.bostatechnicalassessment.data.local.entity.DistrictEntity
import com.example.bostatechnicalassessment.data.remote.ApiCountry
import com.example.bostatechnicalassessment.data.remote.dto.CityDto
import com.example.bostatechnicalassessment.data.remote.dto.DistrictDto
import com.example.bostatechnicalassessment.domain.model.CityModel
import com.example.bostatechnicalassessment.domain.model.DistrictModel
import com.example.bostatechnicalassessment.domain.repository.Country

//region domain(Model) -> data/remote(DTO)
fun Country.toApiCountry(): ApiCountry {
    return ApiCountry.entries[ordinal]
}

fun DistrictDto.toModel(): DistrictModel {
    return DistrictModel(
        zoneId = this.zoneId,
        zoneName = this.zoneName,
        zoneOtherName = this.zoneOtherName,
        districtId = this.districtId,
        districtName = this.districtName,
        districtOtherName = this.districtOtherName,
        dropOffAvailability = this.dropOffAvailability
    )
}

fun CityDto.toModel(): CityModel {
    return CityModel(
        cityId = this.cityId,
        cityName = this.cityName,
        cityOtherName = this.cityOtherName,
        districts = this.districts.map { it.toModel() },
        dropOffAvailability = this.dropOffAvailability
    )
}
//endregion

//region data/local(Entity) -> domain(Model)
fun DistrictEntity.toModel(): DistrictModel {
    return DistrictModel(
        zoneId = zoneId,
        zoneName = zoneName,
        zoneOtherName = zoneOtherName,
        districtId = districtId,
        districtName = districtName,
        districtOtherName = districtOtherName,
        dropOffAvailability = dropOffAvailability
    )
}

fun CityWithDistricts.toModel(): CityModel {
    return CityModel(
        cityId = city.cityId,
        cityName = city.cityName,
        cityOtherName = city.cityOtherName,
        dropOffAvailability = city.dropOffAvailability,
        districts = districts.map { it.toModel() }
    )
}
//endregion

//region domain(Model) -> data/local(Entity)
fun DistrictModel.toEntity(cityOwnerId: String): DistrictEntity {
    return DistrictEntity(
        districtId = districtId,
        zoneId = zoneId,
        zoneName = zoneName,
        zoneOtherName = zoneOtherName,
        districtName = districtName,
        districtOtherName = districtOtherName,
        dropOffAvailability = dropOffAvailability,
        cityOwnerId = cityOwnerId
    )
}

fun CityModel.toEntity(): CityEntity {
    return CityEntity(
        cityId = cityId,
        cityName = cityName,
        cityOtherName = cityOtherName,
        dropOffAvailability = dropOffAvailability
    )
}
//endregion
