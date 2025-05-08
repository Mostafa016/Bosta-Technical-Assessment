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
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MappersTest {

    // --- DTO to Model ---
    @Test
    fun `DistrictDto toModel conversion is correct`() {
        val dto = DistrictDto(
            zoneId = "zone1",
            zoneName = "Zone One",
            zoneOtherName = "Zone One Ar",
            districtId = "dist1",
            districtName = "District Alpha",
            districtOtherName = "District Alpha Ar",
            pickupAvailability = true,
            dropOffAvailability = false
        )
        val model = dto.toModel()
        assertThat(model.zoneId).isEqualTo(dto.zoneId)
        assertThat(model.zoneName).isEqualTo(dto.zoneName)
        assertThat(model.zoneOtherName).isEqualTo(dto.zoneOtherName)
        assertThat(model.districtId).isEqualTo(dto.districtId)
        assertThat(model.districtName).isEqualTo(dto.districtName)
        assertThat(model.districtOtherName).isEqualTo(dto.districtOtherName)
        assertThat(model.dropOffAvailability).isEqualTo(dto.dropOffAvailability)
    }

    @Test
    fun `CityDto toModel conversion is correct`() {
        val districtDto = DistrictDto("z1", "Z", "ZA", "d1", "D", "DA", true, true)
        val cityDto = CityDto(
            cityId = "c1", cityName = "City X", cityOtherName = "City X Ar", cityCode = "CX",
            districts = listOf(districtDto),
            pickupAvailability = true, dropOffAvailability = true
        )
        val model = cityDto.toModel()
        assertThat(model.cityId).isEqualTo(cityDto.cityId)
        assertThat(model.cityName).isEqualTo(cityDto.cityName)
        assertThat(model.cityOtherName).isEqualTo(cityDto.cityOtherName)
        assertThat(model.dropOffAvailability).isEqualTo(cityDto.dropOffAvailability)
        assertThat(model.districts).hasSize(1)
        assertThat(model.districts.first().districtId).isEqualTo(districtDto.districtId)
    }

    @Test
    fun `Country toApiCountry conversion is correct`() {
        assertThat(Country.EGYPT.toApiCountry()).isEqualTo(ApiCountry.EGYPT)
        assertThat(Country.KSA.toApiCountry()).isEqualTo(ApiCountry.KSA)
        assertThat(Country.EGYPT.toApiCountry().countryId).isEqualTo("60e4482c7cb7d4bc4849c4d5")
        assertThat(Country.KSA.toApiCountry().countryId).isEqualTo("eF-3f9FZr")
    }

    // --- Entity to Model ---
    @Test
    fun `DistrictEntity toModel conversion is correct`() {
        val entity = DistrictEntity(
            districtId = "dist_entity_1",
            zoneId = "zone_e1",
            zoneName = "Zone E",
            zoneOtherName = "Zone E Ar",
            districtName = "District Epsilon",
            districtOtherName = "District Epsilon Ar",
            dropOffAvailability = true,
            cityOwnerId = "city_owner_1"
        )
        val model = entity.toModel()
        assertThat(model.districtId).isEqualTo(entity.districtId)
        assertThat(model.zoneId).isEqualTo(entity.zoneId)
        assertThat(model.zoneName).isEqualTo(entity.zoneName)
        assertThat(model.zoneOtherName).isEqualTo(entity.zoneOtherName)
        assertThat(model.districtName).isEqualTo(entity.districtName)
        assertThat(model.districtOtherName).isEqualTo(entity.districtOtherName)
        assertThat(model.dropOffAvailability).isEqualTo(entity.dropOffAvailability)
    }

    @Test
    fun `CityWithDistricts toModel conversion is correct`() {
        val districtEntity = DistrictEntity("de1", "ze1", "ZE", "ZEA", "DE", "DEA", true, "ce1")
        val cityEntity = CityEntity("ce1", "City Entity Y", "City Entity Y Ar", true)
        val cityWithDistricts = CityWithDistricts(cityEntity, listOf(districtEntity))

        val model = cityWithDistricts.toModel()
        assertThat(model.cityId).isEqualTo(cityEntity.cityId)
        assertThat(model.cityName).isEqualTo(cityEntity.cityName)
        assertThat(model.cityOtherName).isEqualTo(cityEntity.cityOtherName)
        assertThat(model.dropOffAvailability).isEqualTo(cityEntity.dropOffAvailability)
        assertThat(model.districts).hasSize(1)
        assertThat(model.districts.first().districtId).isEqualTo(districtEntity.districtId)
    }

    // --- Model to Entity ---
    @Test
    fun `DistrictModel toEntity conversion is correct`() {
        val model = DistrictModel(
            zoneId = "zone_m1",
            zoneName = "Zone M",
            zoneOtherName = "Zone M Ar",
            districtId = "dist_m1",
            districtName = "District Mu",
            districtOtherName = "District Mu Ar",
            dropOffAvailability = false
        )
        val cityOwnerId = "city_owner_for_m1"
        val entity = model.toEntity(cityOwnerId)

        assertThat(entity.districtId).isEqualTo(model.districtId)
        assertThat(entity.zoneId).isEqualTo(model.zoneId)
        assertThat(entity.zoneName).isEqualTo(model.zoneName)
        assertThat(entity.zoneOtherName).isEqualTo(model.zoneOtherName)
        assertThat(entity.districtName).isEqualTo(model.districtName)
        assertThat(entity.districtOtherName).isEqualTo(model.districtOtherName)
        assertThat(entity.dropOffAvailability).isEqualTo(model.dropOffAvailability)
        assertThat(entity.cityOwnerId).isEqualTo(cityOwnerId)
    }

    @Test
    fun `CityModel toEntity conversion is correct`() {
        val districtModel = DistrictModel("zm1", "ZM", "ZMA", "dm1", "DM", "DMA", true)
        val cityModel = CityModel(
            cityId = "cm1", cityName = "City Model Z", cityOtherName = "City Model Z Ar",
            districts = listOf(districtModel), // Note: districts are not part of CityEntity directly
            dropOffAvailability = true
        )
        val entity = cityModel.toEntity()

        assertThat(entity.cityId).isEqualTo(cityModel.cityId)
        assertThat(entity.cityName).isEqualTo(cityModel.cityName)
        assertThat(entity.cityOtherName).isEqualTo(cityModel.cityOtherName)
        assertThat(entity.dropOffAvailability).isEqualTo(cityModel.dropOffAvailability)
    }
}