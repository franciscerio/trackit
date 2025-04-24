package com.fcerio.trackit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fcerio.core.common.compose.TrackitTheme
import com.fcerio.trackit.features.main.composable.MainScreenComposable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Change status bar color
        enableEdgeToEdge()
        setContent {
            TrackitTheme {
                MainScreenComposable()
            }
        }
    }
}
