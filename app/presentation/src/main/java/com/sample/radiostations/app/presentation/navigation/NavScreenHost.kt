package com.sample.radiostations.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sample.radiostations.app.presentation.radiostation.radioStationNavGraph
import com.sample.radiostations.app.presentation.radiostation.list.RadioStationListNavHostInfo

@Composable
fun NavScreenHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = RadioStationListNavHostInfo.ROUTE) {
        radioStationNavGraph(navController)
    }
}
