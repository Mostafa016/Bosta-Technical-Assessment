package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// TODO: Toggle list for pickup and drop off (It's choose the delivery area
//  , it's drop off not pickup)
@Composable
fun CityList(modifier: Modifier = Modifier, cities: List<CityModel>) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(cities, key = { _, item -> item.cityId }) { index, city ->
            // TODO: Change stuff for viewmodel
            CityListItem(Modifier.fillMaxWidth(), cityModel = city, isExpanded = (index % 2 == 0))
            // TODO: Replace this when implementing API
            if (index < cities.lastIndex && (index % 2 != 0))
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
        }
    }
}


data class CityModel(
    val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val cityCode: String,
    val districts: List<DistrictModel>
)

data class DistrictModel(
    val zoneId: String,
    val zoneName: String,
    val zoneOtherName: String,
    val districtId: String,
    val districtName: String,
    val districtOtherName: String,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
    val coverage: String
)