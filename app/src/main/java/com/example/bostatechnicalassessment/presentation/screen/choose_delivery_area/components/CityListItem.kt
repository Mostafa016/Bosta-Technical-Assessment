package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.bostatechnicalassessment.R
import com.example.bostatechnicalassessment.domain.model.CityModel
import com.example.bostatechnicalassessment.domain.model.DistrictModel
import com.example.bostatechnicalassessment.presentation.theme.LIST_ITEM_PADDING

@Composable
fun CityListItem(
    modifier: Modifier = Modifier,
    cityModel: CityModel,
    isExpanded: Boolean,
    onToggleCityDistrictsVisibility: (String) -> Unit,
) {
    Row(
        modifier = modifier.padding(LIST_ITEM_PADDING),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            cityModel.cityName,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        ExpandDistrictListIconButton(
            isExpanded = isExpanded,
            onToggleCityDistrictsVisibility = { onToggleCityDistrictsVisibility(cityModel.cityId) })
    }
    if (isExpanded) {
        DistrictList(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.25f)),
            districtList = cityModel.districts
        )
    }
}

@Composable
private fun ExpandDistrictListIconButton(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onToggleCityDistrictsVisibility: () -> Unit,
) {
    IconButton(
        modifier = modifier, onClick = onToggleCityDistrictsVisibility
    ) {
        Icon(
            if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = stringResource(R.string.show_city_districts),
            tint = Color.Black
        )
    }
}

// --- Previews ---

@Preview(name = "CityListItemCollapsedPreview", showBackground = true)
@Composable
fun CityListItemCollapsedPreview() {
    MaterialTheme { // Wrap in MaterialTheme for proper styling
        CityListItem(
            cityModel = CityModel(
                cityName = "Sample City",
                cityId = "1",
                cityOtherName = "Sample City",
                districts = listOf(
                    DistrictModel(
                        zoneId = "9mih4NXL1GF",
                        zoneName = "Abu Yousef",
                        zoneOtherName = "ابو يوسف",
                        districtId = "zoJP71_5Ca1",
                        districtName = "Abu Yousef",
                        districtOtherName = "ابو يوسف",
                        dropOffAvailability = true,
                    ),
                    DistrictModel(
                        zoneId = "alex_sm",
                        zoneName = "Smoha",
                        zoneOtherName = "سموحة",
                        districtId = "alex_sm_id",
                        districtName = "Smoha",
                        districtOtherName = "سموحة",
                        dropOffAvailability = true,
                    )
                ),
                dropOffAvailability = true
            ),
            isExpanded = false,
            onToggleCityDistrictsVisibility = {},
        )
    }
}

@Preview(name = "CityListItemExpandedPreview", showBackground = true)
@Composable
fun CityListItemExpandedPreview() {
    MaterialTheme { // Wrap in MaterialTheme for proper styling
        CityListItem(
            cityModel = CityModel(
                cityName = "Sample City",
                cityId = "2",
                cityOtherName = "Sample City",
                districts = listOf(
                    DistrictModel(
                        zoneId = "9mih4NXL1GF",
                        zoneName = "Abu Yousef",
                        zoneOtherName = "ابو يوسف",
                        districtId = "zoJP71_5Ca1",
                        districtName = "Abu Yousef",
                        districtOtherName = "ابو يوسف",
                        dropOffAvailability = true,
                    ),
                    DistrictModel(
                        zoneId = "alex_sm",
                        zoneName = "Smoha",
                        zoneOtherName = "سموحة",
                        districtId = "alex_sm_id",
                        districtName = "Smoha",
                        districtOtherName = "سموحة",
                        dropOffAvailability = true,
                    )
                ),
                dropOffAvailability = true
            ),
            isExpanded = true,
            onToggleCityDistrictsVisibility = {},
        )
    }
}

@Preview(name = "ExpandDistrictListIconButtonCollapsedPreview", showBackground = true)
@Composable
fun ExpandDistrictListIconButtonCollapsedPreview() {
    MaterialTheme { // Wrap in MaterialTheme for proper styling
        ExpandDistrictListIconButton(
            isExpanded = false,
            onToggleCityDistrictsVisibility = {},
        )
    }
}

@Preview(name = "ExpandDistrictListIconButtonExpandedPreview", showBackground = true)
@Composable
fun ExpandDistrictListIconButtonExpandedPreview() {
    MaterialTheme { // Wrap in MaterialTheme for proper styling
        ExpandDistrictListIconButton(
            isExpanded = true,
            onToggleCityDistrictsVisibility = {},
        )
    }
}