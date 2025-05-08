package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostatechnicalassessment.core.util.Resource
import com.example.bostatechnicalassessment.domain.repository.CityRepository
import com.example.bostatechnicalassessment.domain.repository.Country
import com.example.bostatechnicalassessment.presentation.utils.DebuggingTags
import com.example.bostatechnicalassessment.presentation.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ChooseDeliveryAreaViewModel @Inject constructor(private val cityRepository: CityRepository) :
    ViewModel() {
    private val _state: MutableStateFlow<ChooseDeliveryAreaState> = MutableStateFlow(
        ChooseDeliveryAreaState()
    )
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private var searchJob: Job? = null

    init {
        getAllDistricts()
    }

    fun onEvent(event: ChooseDeliveryAreaEvent) {
        when (event) {
            is ChooseDeliveryAreaEvent.ChangeSearchText -> {
                _state.value = state.value.copy(searchText = event.text)
                viewModelScope.launch(Dispatchers.IO) {
                    delay(DELAY_BEFORE_RESEARCHING_MS)
                    searchCityOrDistrict(state.value.searchText)
                    delay(300)
                    Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, state.value.cities.toString())
                }
            }

            ChooseDeliveryAreaEvent.StartSearch -> {
                Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, "Started search")
                searchCityOrDistrict(state.value.searchText)
                Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, state.value.cities.toString())
            }

            is ChooseDeliveryAreaEvent.SelectCityDistrict -> {
                _state.value =
                    state.value.copy(
                        selectedDistrict = state.value.cities
                            .find { it.cityId == event.cityId }
                            ?.districts
                            ?.find { it.districtId == event.districtId }
                    )
            }

            is ChooseDeliveryAreaEvent.ToggleCityDistrictsVisibility -> {
                val cityDistrictsVisibility =
                    state.value.cityDistrictsVisibility.toMutableMap().apply {
                        val isVisible = get(event.cityId)!!
                        put(event.cityId, !isVisible)
                    }
                _state.value = state.value.copy(cityDistrictsVisibility = cityDistrictsVisibility)
            }
        }
    }

    private fun searchCityOrDistrict(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            cityRepository.searchForCityOrDistrict(query).onEach { result ->
                Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, result.toString())
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                        Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, "List Loading")
                    }

                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            cities = result.data!!,
                            cityDistrictsVisibility = result.data.associate { it.cityId to false },
                        )
                        Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, "List Success")
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                        )
                        _uiEvent.emit(UiEvent.ShowSnackBar(result.message ?: "Unknown Error"))
                        Log.e(
                            DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN,
                            "List Error: ${result.message ?: "Unknown Error"}"
                        )
                    }
                }
            }.collect()
        }
    }

    private fun getAllDistricts() {
        viewModelScope.launch(Dispatchers.IO) {
            cityRepository.getAllDistricts(Country.EGYPT).onEach { result ->
                Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, result.toString())
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                        Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, "List Loading")
                    }

                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            cities = result.data!!,
                            cityDistrictsVisibility = result.data.associate { it.cityId to false },
                        )
                        Log.d(DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN, "List Success")
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                        )
                        _uiEvent.emit(UiEvent.ShowSnackBar(result.message ?: "Unknown Error"))
                        Log.e(
                            DebuggingTags.CHOOSE_DELIVERY_AREA_SCREEN,
                            "List Error: ${result.message ?: "Unknown Error"}"
                        )
                    }
                }
            }.collect()
        }
    }

    companion object {
        const val DELAY_BEFORE_RESEARCHING_MS = 200L
    }
}