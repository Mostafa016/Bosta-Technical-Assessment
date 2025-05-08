package com.example.bostatechnicalassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.ChooseDeliveryAreaScreen
import com.example.bostatechnicalassessment.presentation.theme.BostaTechnicalAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BostaTechnicalAssessmentTheme {
                ChooseDeliveryAreaScreen()
            }
        }
    }
}