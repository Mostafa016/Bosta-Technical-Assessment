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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bostatechnicalassessment.R
import com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components.AppTopAppBar
import com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components.CityList
import com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components.SearchBar
import com.example.bostatechnicalassessment.presentation.theme.SCREEN_PADDING
import com.example.bostatechnicalassessment.presentation.theme.SCREEN_VERTICAL_SPACING
import com.example.bostatechnicalassessment.presentation.utils.DebuggingTags
import com.example.bostatechnicalassessment.presentation.utils.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ChooseDeliveryAreaScreen(
    modifier: Modifier = Modifier,
    viewModel: ChooseDeliveryAreaViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            AppTopAppBar(
                Modifier.fillMaxWidth(),
                title = stringResource(R.string.choose_the_delivery_area),
                onClose = {
                    Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, "Closed")
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        LaunchedEffect(true) {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEvent.ShowSnackBar -> {
                        snackbarHostState.showSnackbar(
                            message = event.message, duration = SnackbarDuration.Short,
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(SCREEN_PADDING)
                .fillMaxSize()
        ) {
            val state by viewModel.state.collectAsStateWithLifecycle()
            SearchBar(
                modifier = Modifier.fillMaxWidth(), text = state.searchText,
                onValueChange = { viewModel.onEvent(ChooseDeliveryAreaEvent.ChangeSearchText(it)) },
                onSearchClicked = { viewModel.onEvent(ChooseDeliveryAreaEvent.StartSearch) }
            )
            Spacer(Modifier.height(SCREEN_VERTICAL_SPACING))
            CityList(
                modifier = Modifier.fillMaxSize(),
                cities = state.cities,
                cityDistrictsVisibility = state.cityDistrictsVisibility,
                onToggleCityDistrictsVisibility = {
                    viewModel.onEvent(
                        ChooseDeliveryAreaEvent.ToggleCityDistrictsVisibility(
                            it
                        )
                    )
                }
            )
        }
    }
}

//// Function to generate a list of sample City objects with more districts
//fun createMoreCityListWithMoreDistricts(): List<CityModel> {
//    // Districts for Alexandria (aiming for around 5)
//    val alexandriaDistricts = listOf(
//        DistrictModel(
//            zoneId = "9mih4NXL1GF",
//            zoneName = "Abu Yousef",
//            zoneOtherName = "ابو يوسف",
//            districtId = "zoJP71_5Ca1",
//            districtName = "Abu Yousef",
//            districtOtherName = "ابو يوسف",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "alex_sm",
//            zoneName = "Smoha",
//            zoneOtherName = "سموحة",
//            districtId = "alex_sm_id",
//            districtName = "Smoha",
//            districtOtherName = "سموحة",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "alex_mf",
//            zoneName = "Miami Flemeng",
//            zoneOtherName = "ميامي فلمنج",
//            districtId = "alex_mf_id",
//            districtName = "Miami Flemeng",
//            districtOtherName = "ميامي فلمنج",
//            dropOffAvailability = false, // Example: Drop-off not available
//        ),
//        DistrictModel(
//            zoneId = "alex_gl",
//            zoneName = "Glim Bay",
//            zoneOtherName = "خليج جليم",
//            districtId = "alex_gl_id",
//            districtName = "Glim Bay",
//            districtOtherName = "خليج جليم",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "alex_sy",
//            zoneName = "Sidi Bishr",
//            zoneOtherName = "سيدي بشر",
//            districtId = "alex_sy_id",
//            districtName = "Sidi Bishr",
//            districtOtherName = "سيدي بشر",
//            dropOffAvailability = true,
//        )
//    )
//
//    // Districts for Cairo (aiming for around 5)
//    val cairoDistricts = listOf(
//        DistrictModel(
//            zoneId = "cairo_zm",
//            zoneName = "Zamalek",
//            zoneOtherName = "الزمالك",
//            districtId = "cairo_zm_id",
//            districtName = "Zamalek",
//            districtOtherName = "الزمالك",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "cairo_ma",
//            zoneName = "Maadi",
//            zoneOtherName = "المعادي",
//            districtId = "cairo_ma_id",
//            districtName = "Maadi",
//            districtOtherName = "المعادي",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "cairo_nsr",
//            zoneName = "Nasr City",
//            zoneOtherName = "مدينة نصر",
//            districtId = "cairo_nsr_id",
//            districtName = "Nasr City",
//            districtOtherName = "مدينة نصر",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "cairo_hl",
//            zoneName = "Heliopolis",
//            zoneOtherName = "مصر الجديدة",
//            districtId = "cairo_hl_id",
//            districtName = "Heliopolis",
//            districtOtherName = "مصر الجديدة",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "cairo_sh",
//            zoneName = "Shubra",
//            zoneOtherName = "شبرا",
//            districtId = "cairo_sh_id",
//            districtName = "Shubra",
//            districtOtherName = "شبرا",
//            dropOffAvailability = true,
//        )
//    )
//
//    // Districts for Giza (aiming for around 5)
//    val gizaDistricts = listOf(
//        DistrictModel(
//            zoneId = "giza_dk",
//            zoneName = "Dokki",
//            zoneOtherName = "الدقي",
//            districtId = "giza_dk_id",
//            districtName = "Dokki",
//            districtOtherName = "الدقي",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "giza_ag",
//            zoneName = "Agouza",
//            zoneOtherName = "العجوزة",
//            districtId = "giza_ag_id",
//            districtName = "Agouza",
//            districtOtherName = "العجوزة",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "giza_hr",
//            zoneName = "Haram",
//            zoneOtherName = "الهرم",
//            districtId = "giza_hr_id",
//            districtName = "Haram",
//            districtOtherName = "الهرم",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "giza_shz",
//            zoneName = "Sheikh Zayed",
//            zoneOtherName = "الشيخ زايد",
//            districtId = "giza_shz_id",
//            districtName = "Sheikh Zayed",
//            districtOtherName = "الشيخ زايد",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "giza_6oct",
//            zoneName = "6th of October City",
//            zoneOtherName = "مدينة 6 أكتوبر",
//            districtId = "giza_6oct_id",
//            districtName = "6th of October City",
//            districtOtherName = "مدينة 6 أكتوبر",
//            dropOffAvailability = true,
//        )
//    )
//
//    // Districts for Shubra El Kheima (aiming for around 5)
//    val shubraElKheimaDistricts = listOf(
//        DistrictModel(
//            zoneId = "sek_main",
//            zoneName = "Shubra El Kheima Main",
//            zoneOtherName = "شبرا الخيمة الرئيسي",
//            districtId = "sek_main_id",
//            districtName = "Shubra El Kheima Main",
//            districtOtherName = "شبرا الخيمة الرئيسي",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "sek_east",
//            zoneName = "East Shubra El Kheima",
//            zoneOtherName = "شرق شبرا الخيمة",
//            districtId = "sek_east_id",
//            districtName = "East Shubra El Kheima",
//            districtOtherName = "شرق شبرا الخيمة",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "sek_west",
//            zoneName = "West Shubra El Kheima",
//            zoneOtherName = "غرب شبرا الخيمة",
//            districtId = "sek_west_id",
//            districtName = "West Shubra El Kheima",
//            districtOtherName = "غرب شبرا الخيمة",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "sek_naser",
//            zoneName = "Manshiyat Naser (Shubra)", // Often associated
//            zoneOtherName = "منشية ناصر",
//            districtId = "sek_naser_id",
//            districtName = "Manshiyat Naser (Shubra)",
//            districtOtherName = "منشية ناصر",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "sek_sayeda",
//            zoneName = "Sayeda Aisha (Shubra)", // Often associated
//            zoneOtherName = "السيدة عائشة",
//            districtId = "sek_sayeda_id",
//            districtName = "Sayeda Aisha (Shubra)",
//            districtOtherName = "السيدة عائشة",
//            dropOffAvailability = false,
//        )
//    )
//
//    // Districts for Port Said (aiming for around 5)
//    val portSaidDistricts = listOf(
//        DistrictModel(
//            zoneId = "ps_sharq",
//            zoneName = "Al-Sharq",
//            zoneOtherName = "الشرق",
//            districtId = "ps_sharq_id",
//            districtName = "Al-Sharq",
//            districtOtherName = "الشرق",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "ps_arab",
//            zoneName = "Al-Arab",
//            zoneOtherName = "العرب",
//            districtId = "ps_arab_id",
//            districtName = "Al-Arab",
//            districtOtherName = "العرب",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "ps_manakh",
//            zoneName = "Al-Manakh",
//            zoneOtherName = "المناخ",
//            districtId = "ps_manakh_id",
//            districtName = "Al-Manakh",
//            districtOtherName = "المناخ",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "ps_dawahi",
//            zoneName = "Al-Dawahi",
//            zoneOtherName = "الضواحي",
//            districtId = "ps_dawahi_id",
//            districtName = "Al-Dawahi",
//            districtOtherName = "الضواحي",
//            dropOffAvailability = true,
//        ),
//        DistrictModel(
//            zoneId = "ps_zuhur",
//            zoneName = "Al-Zuhur",
//            zoneOtherName = "الزهور",
//            districtId = "ps_zuhur_id",
//            districtName = "Al-Zuhur",
//            districtOtherName = "الزهور",
//            dropOffAvailability = true,
//        )
//    )
//
//
//    // Create City objects
//    val alexandriaCity = CityModel(
//        cityId = "Jrb6X6ucjiYgMP4T7",
//        cityName = "Alexandria",
//        cityOtherName = "الاسكندريه",
//        dropOffAvailability = true,
//        districts = alexandriaDistricts
//    )
//
//    val cairoCity = CityModel(
//        cityId = "some_cairo_id", // Sample ID
//        cityName = "Cairo",
//        cityOtherName = "القاهرة",
//        dropOffAvailability = true,
//        districts = cairoDistricts
//    )
//
//    val gizaCity = CityModel(
//        cityId = "some_giza_id", // Sample ID
//        cityName = "Giza",
//        cityOtherName = "الجيزة",
//        dropOffAvailability = true,
//        districts = gizaDistricts
//    )
//    val shubraElKheimaCity = CityModel(
//        cityId = "some_sek_id", // Sample ID
//        cityName = "Shubra El Kheima",
//        cityOtherName = "شبرا الخيمة",
//        dropOffAvailability = true,
//        districts = shubraElKheimaDistricts
//    )
//
//    val portSaidCity = CityModel(
//        cityId = "some_ps_id", // Sample ID
//        cityName = "Port Said",
//        cityOtherName = "بور سعيد",
//        dropOffAvailability = true,
//        districts = portSaidDistricts
//    )
//
//
//    // Return a list containing all the sample cities
//    return listOf(alexandriaCity, cairoCity, gizaCity, shubraElKheimaCity, portSaidCity)
//}

@Preview(showBackground = true)
@Composable
fun ChooseDeliveryAreaScreenPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        ChooseDeliveryAreaScreen(Modifier.fillMaxSize())
    }
}