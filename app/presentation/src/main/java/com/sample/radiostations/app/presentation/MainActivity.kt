package com.sample.radiostations.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.radiostations.core.design.system.RadioStationsTheme
import com.sample.radiostations.app.presentation.navigation.NavScreenHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ()
        setContent {
            RadioStationsTheme {
                NavScreenHost(navController = rememberNavController())
            }
        }
    }
}
