package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area

import com.example.bostatechnicalassessment.domain.model.CityModel
import com.example.bostatechnicalassessment.domain.model.DistrictModel

data class ChooseDeliveryAreaState(
    val searchText: String = "",
    val isLoading: Boolean = true,
    val cities: List<CityModel> = emptyList(),
    val cityDistrictsVisibility: Map<String, Boolean> = emptyMap(), // cityId, isVisible
    val selectedDistrict: DistrictModel? = null,
)
