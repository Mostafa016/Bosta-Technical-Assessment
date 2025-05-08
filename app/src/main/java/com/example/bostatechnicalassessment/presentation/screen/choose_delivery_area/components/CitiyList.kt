package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bostatechnicalassessment.domain.model.CityModel

@Composable
fun CityList(
    modifier: Modifier = Modifier,
    cities: List<CityModel>,
    cityDistrictsVisibility: Map<String, Boolean>,
    onToggleCityDistrictsVisibility: (String) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(cities, key = { _, item -> item.cityId }) { index, city ->
            CityListItem(
                Modifier.fillMaxWidth(),
                cityModel = city,
                isExpanded = cityDistrictsVisibility[city.cityId]!!,
                onToggleCityDistrictsVisibility = onToggleCityDistrictsVisibility
            )
            if (index < cities.lastIndex && !cityDistrictsVisibility[city.cityId]!!)
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
        }
    }
}


