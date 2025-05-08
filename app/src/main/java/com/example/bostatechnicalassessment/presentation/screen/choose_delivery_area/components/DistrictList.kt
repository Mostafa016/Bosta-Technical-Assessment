package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bostatechnicalassessment.domain.model.DistrictModel
import com.example.bostatechnicalassessment.presentation.theme.LIST_ITEM_VERTICAL_SPACE
import com.example.bostatechnicalassessment.presentation.theme.LIST_PADDING

@Composable
fun DistrictList(modifier: Modifier = Modifier, districtList: List<DistrictModel>) {
    Column(modifier = modifier.padding(LIST_PADDING)) {
        districtList.forEachIndexed { index, district ->
            DistrictListItem(
                modifier = Modifier.fillMaxWidth(),
                zoneName = district.zoneName,
                districtName = district.districtName,
                isCovered = district.dropOffAvailability
            )
            if (index < districtList.lastIndex)
                Spacer(Modifier.height(LIST_ITEM_VERTICAL_SPACE))
        }
    }
}

@Preview(name = "DistrictListPreview", showBackground = true)
@Composable
fun DistrictListPreview() {
    MaterialTheme { // Wrap in MaterialTheme for proper styling
        DistrictList(
            districtList = listOf(
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
                ),
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
            )
        )
    }
}