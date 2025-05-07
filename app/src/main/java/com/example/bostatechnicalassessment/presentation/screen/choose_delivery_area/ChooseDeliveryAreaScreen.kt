package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bostatechnicalassessment.R
import com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components.AppTopAppBar
import com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components.CityList
import com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components.CityModel
import com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components.DistrictModel
import com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components.SearchBar
import com.example.bostatechnicalassessment.presentation.theme.SCREEN_PADDING
import com.example.bostatechnicalassessment.presentation.theme.SCREEN_VERTICAL_SPACING
import com.example.bostatechnicalassessment.presentation.utils.DebuggingTags

@Composable
fun ChooseDeliveryAreaScreen(modifier: Modifier = Modifier) {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            AppTopAppBar(
                Modifier.fillMaxWidth(),
                title = stringResource(R.string.choose_the_delivery_area),
                onClose = {
                    // TODO: Add closing logic
                    Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, "Closed")
                },
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(SCREEN_PADDING)
                .fillMaxSize()
        ) {
            SearchBar(modifier = Modifier.fillMaxWidth(), text = "", onValueChange = {})
            Spacer(Modifier.height(SCREEN_VERTICAL_SPACING))
            CityList(
                modifier = Modifier.fillMaxSize(),
                cities = createMoreCityListWithMoreDistricts()
            )
        }
    }
}

// Function to generate a list of sample City objects with more districts
fun createMoreCityListWithMoreDistricts(): List<CityModel> {
    // Districts for Alexandria (aiming for around 5)
    val alexandriaDistricts = listOf(
        DistrictModel(
            zoneId = "9mih4NXL1GF",
            zoneName = "Abu Yousef",
            zoneOtherName = "ابو يوسف",
            districtId = "zoJP71_5Ca1",
            districtName = "Abu Yousef",
            districtOtherName = "ابو يوسف",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "alex_sm",
            zoneName = "Smoha",
            zoneOtherName = "سموحة",
            districtId = "alex_sm_id",
            districtName = "Smoha",
            districtOtherName = "سموحة",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "alex_mf",
            zoneName = "Miami Flemeng",
            zoneOtherName = "ميامي فلمنج",
            districtId = "alex_mf_id",
            districtName = "Miami Flemeng",
            districtOtherName = "ميامي فلمنج",
            pickupAvailability = true,
            dropOffAvailability = false, // Example: Drop-off not available
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "alex_gl",
            zoneName = "Glim Bay",
            zoneOtherName = "خليج جليم",
            districtId = "alex_gl_id",
            districtName = "Glim Bay",
            districtOtherName = "خليج جليم",
            pickupAvailability = false, // Example: Pickup not available
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "alex_sy",
            zoneName = "Sidi Bishr",
            zoneOtherName = "سيدي بشر",
            districtId = "alex_sy_id",
            districtName = "Sidi Bishr",
            districtOtherName = "سيدي بشر",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        )
    )

    // Districts for Cairo (aiming for around 5)
    val cairoDistricts = listOf(
        DistrictModel(
            zoneId = "cairo_zm",
            zoneName = "Zamalek",
            zoneOtherName = "الزمالك",
            districtId = "cairo_zm_id",
            districtName = "Zamalek",
            districtOtherName = "الزمالك",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "cairo_ma",
            zoneName = "Maadi",
            zoneOtherName = "المعادي",
            districtId = "cairo_ma_id",
            districtName = "Maadi",
            districtOtherName = "المعادي",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "cairo_nsr",
            zoneName = "Nasr City",
            zoneOtherName = "مدينة نصر",
            districtId = "cairo_nsr_id",
            districtName = "Nasr City",
            districtOtherName = "مدينة نصر",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "cairo_hl",
            zoneName = "Heliopolis",
            zoneOtherName = "مصر الجديدة",
            districtId = "cairo_hl_id",
            districtName = "Heliopolis",
            districtOtherName = "مصر الجديدة",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "cairo_sh",
            zoneName = "Shubra",
            zoneOtherName = "شبرا",
            districtId = "cairo_sh_id",
            districtName = "Shubra",
            districtOtherName = "شبرا",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        )
    )

    // Districts for Giza (aiming for around 5)
    val gizaDistricts = listOf(
        DistrictModel(
            zoneId = "giza_dk",
            zoneName = "Dokki",
            zoneOtherName = "الدقي",
            districtId = "giza_dk_id",
            districtName = "Dokki",
            districtOtherName = "الدقي",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "giza_ag",
            zoneName = "Agouza",
            zoneOtherName = "العجوزة",
            districtId = "giza_ag_id",
            districtName = "Agouza",
            districtOtherName = "العجوزة",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "giza_hr",
            zoneName = "Haram",
            zoneOtherName = "الهرم",
            districtId = "giza_hr_id",
            districtName = "Haram",
            districtOtherName = "الهرم",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "giza_shz",
            zoneName = "Sheikh Zayed",
            zoneOtherName = "الشيخ زايد",
            districtId = "giza_shz_id",
            districtName = "Sheikh Zayed",
            districtOtherName = "الشيخ زايد",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "giza_6oct",
            zoneName = "6th of October City",
            zoneOtherName = "مدينة 6 أكتوبر",
            districtId = "giza_6oct_id",
            districtName = "6th of October City",
            districtOtherName = "مدينة 6 أكتوبر",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        )
    )

    // Districts for Shubra El Kheima (aiming for around 5)
    val shubraElKheimaDistricts = listOf(
        DistrictModel(
            zoneId = "sek_main",
            zoneName = "Shubra El Kheima Main",
            zoneOtherName = "شبرا الخيمة الرئيسي",
            districtId = "sek_main_id",
            districtName = "Shubra El Kheima Main",
            districtOtherName = "شبرا الخيمة الرئيسي",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "sek_east",
            zoneName = "East Shubra El Kheima",
            zoneOtherName = "شرق شبرا الخيمة",
            districtId = "sek_east_id",
            districtName = "East Shubra El Kheima",
            districtOtherName = "شرق شبرا الخيمة",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "sek_west",
            zoneName = "West Shubra El Kheima",
            zoneOtherName = "غرب شبرا الخيمة",
            districtId = "sek_west_id",
            districtName = "West Shubra El Kheima",
            districtOtherName = "غرب شبرا الخيمة",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "sek_naser",
            zoneName = "Manshiyat Naser (Shubra)", // Often associated
            zoneOtherName = "منشية ناصر",
            districtId = "sek_naser_id",
            districtName = "Manshiyat Naser (Shubra)",
            districtOtherName = "منشية ناصر",
            pickupAvailability = false,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "sek_sayeda",
            zoneName = "Sayeda Aisha (Shubra)", // Often associated
            zoneOtherName = "السيدة عائشة",
            districtId = "sek_sayeda_id",
            districtName = "Sayeda Aisha (Shubra)",
            districtOtherName = "السيدة عائشة",
            pickupAvailability = true,
            dropOffAvailability = false,
            coverage = "BOSTA"
        )
    )

    // Districts for Port Said (aiming for around 5)
    val portSaidDistricts = listOf(
        DistrictModel(
            zoneId = "ps_sharq",
            zoneName = "Al-Sharq",
            zoneOtherName = "الشرق",
            districtId = "ps_sharq_id",
            districtName = "Al-Sharq",
            districtOtherName = "الشرق",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "ps_arab",
            zoneName = "Al-Arab",
            zoneOtherName = "العرب",
            districtId = "ps_arab_id",
            districtName = "Al-Arab",
            districtOtherName = "العرب",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "ps_manakh",
            zoneName = "Al-Manakh",
            zoneOtherName = "المناخ",
            districtId = "ps_manakh_id",
            districtName = "Al-Manakh",
            districtOtherName = "المناخ",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "ps_dawahi",
            zoneName = "Al-Dawahi",
            zoneOtherName = "الضواحي",
            districtId = "ps_dawahi_id",
            districtName = "Al-Dawahi",
            districtOtherName = "الضواحي",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        ),
        DistrictModel(
            zoneId = "ps_zuhur",
            zoneName = "Al-Zuhur",
            zoneOtherName = "الزهور",
            districtId = "ps_zuhur_id",
            districtName = "Al-Zuhur",
            districtOtherName = "الزهور",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        )
    )


    // Create City objects
    val alexandriaCity = CityModel(
        cityId = "Jrb6X6ucjiYgMP4T7",
        cityName = "Alexandria",
        cityOtherName = "الاسكندريه",
        cityCode = "EG-02",
        districts = alexandriaDistricts
    )

    val cairoCity = CityModel(
        cityId = "some_cairo_id", // Sample ID
        cityName = "Cairo",
        cityOtherName = "القاهرة",
        cityCode = "EG-01",
        districts = cairoDistricts
    )

    val gizaCity = CityModel(
        cityId = "some_giza_id", // Sample ID
        cityName = "Giza",
        cityOtherName = "الجيزة",
        cityCode = "EG-03", // Sample Code
        districts = gizaDistricts
    )
    val shubraElKheimaCity = CityModel(
        cityId = "some_sek_id", // Sample ID
        cityName = "Shubra El Kheima",
        cityOtherName = "شبرا الخيمة",
        cityCode = "EG-??", // Sample Code (Qalyubia Governorate)
        districts = shubraElKheimaDistricts
    )

    val portSaidCity = CityModel(
        cityId = "some_ps_id", // Sample ID
        cityName = "Port Said",
        cityOtherName = "بور سعيد",
        cityCode = "EG-??", // Sample Code (Port Said Governorate)
        districts = portSaidDistricts
    )


    // Return a list containing all the sample cities
    return listOf(alexandriaCity, cairoCity, gizaCity, shubraElKheimaCity, portSaidCity)
}

@Preview(showBackground = true)
@Composable
fun ChooseDeliveryAreaScreenPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        ChooseDeliveryAreaScreen(Modifier.fillMaxSize())
    }
}