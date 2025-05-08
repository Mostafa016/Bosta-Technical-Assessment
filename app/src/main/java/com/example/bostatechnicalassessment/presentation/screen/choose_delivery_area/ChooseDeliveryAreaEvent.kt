package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area

sealed interface ChooseDeliveryAreaEvent {
    data class ChangeSearchText(val text: String) : ChooseDeliveryAreaEvent
    data object StartSearch : ChooseDeliveryAreaEvent
    data class ToggleCityDistrictsVisibility(val cityId: String) : ChooseDeliveryAreaEvent
    data class SelectCityDistrict(val cityId: String, val districtId: String) :
        ChooseDeliveryAreaEvent
}