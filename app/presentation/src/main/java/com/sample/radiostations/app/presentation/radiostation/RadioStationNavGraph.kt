package com.sample.radiostations.app.presentation.radiostation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sample.radiostations.app.presentation.radiostation.list.RadioStationListNavHostInfo
import com.sample.radiostations.app.presentation.radiostation.list.RadioStationsListScreenRoute
import com.sample.radiostations.app.presentation.radiostation.shows.RadioStationShowsNavHostInfo
import com.sample.radiostations.app.presentation.radiostation.shows.RadioStationShowsScreenRoute
import com.sample.radiostations.app.presentation.radiostation.shows.navigateToRadioStationShows

internal fun NavGraphBuilder.radioStationNavGraph(
    navController: NavHostController
) {
    composable(
        route = RadioStationListNavHostInfo.ROUTE
    ) {
        RadioStationsListScreenRoute(
            onClickRadioStation = navController::navigateToRadioStationShows
        )
    }

    composable(
        route = RadioStationShowsNavHostInfo.ROUTE,
        arguments = listOf(
            navArgument(RadioStationShowsNavHostInfo.RADIO_STATION_ID_PARAM) { type = NavType.StringType }
        )
    ) {
        RadioStationShowsScreenRoute()
    }
}
