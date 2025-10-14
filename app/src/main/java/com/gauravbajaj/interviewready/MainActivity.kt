package com.gauravbajaj.interviewready

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.gauravbajaj.interviewready.navigation.InterviewReadyNavHost
import com.gauravbajaj.interviewready.ui.theme.InterviewReadyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewReadyTheme {
                InterviewReadyNavHost()
            }
        }
    }
}

@Composable
fun InterviewReadyApp() {
    InterviewReadyNavHost()
}

@Composable
fun InterviewReadyPreview() {
    InterviewReadyTheme {
        InterviewReadyApp()
    }
}