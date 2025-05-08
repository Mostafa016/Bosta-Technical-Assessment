package com.example.bostatechnicalassessment.presentation.utils

sealed interface UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent
}