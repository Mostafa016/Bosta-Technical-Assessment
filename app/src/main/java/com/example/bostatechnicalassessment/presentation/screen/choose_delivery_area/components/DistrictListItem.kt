package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bostatechnicalassessment.R
import com.example.bostatechnicalassessment.presentation.theme.LABEL_PADDING
import com.example.bostatechnicalassessment.presentation.theme.LIST_ITEM_PADDING
import com.example.bostatechnicalassessment.presentation.theme.ROUNDED_CORNER_DIMS

@Composable
fun DistrictListItem(
    modifier: Modifier = Modifier,
    zoneName: String,
    districtName: String,
    isCovered: Boolean,
) {
    Row(
        modifier = modifier.padding(vertical = LIST_ITEM_PADDING),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "$zoneName - $districtName",
            style = MaterialTheme.typography.bodyLarge,
            color = if (isCovered) MaterialTheme.colorScheme.onBackground else Color.Gray
        )
        if (!isCovered) {
            UncoveredLabel()
        }
    }
}

@Composable
private fun UncoveredLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(ROUNDED_CORNER_DIMS))
            .padding(LABEL_PADDING),
        text = stringResource(R.string.uncovered),
        style = MaterialTheme.typography.labelMedium,
        color = Color.Gray
    )
}


//region Previews

@Preview(name = "DistrictListItemPreview", showBackground = true)
@Composable
private fun DistrictListItemPreview() {
    MaterialTheme { // Wrap in MaterialTheme for proper styling
        DistrictListItem(
            zoneName = "Zone A",
            districtName = "District 1",
            isCovered = false
        )
    }
}

@Preview(name = "DistrictListItemCoveredPreview", showBackground = true)
@Composable
private fun DistrictListItemCoveredPreview() {
    MaterialTheme { // Wrap in MaterialTheme for proper styling
        DistrictListItem(
            zoneName = "Zone B",
            districtName = "District 2",
            isCovered = true
        )
    }
}


@Preview(name = "UncoveredLabelPreview", showBackground = true)
@Composable
private fun UncoveredLabelPreview() {
    MaterialTheme { // Wrap in MaterialTheme for proper styling
        UncoveredLabel()
    }
}
//endregion