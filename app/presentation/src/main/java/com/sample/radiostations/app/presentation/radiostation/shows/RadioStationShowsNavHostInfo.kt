package com.sample.radiostations.app.presentation.radiostation.shows

import androidx.navigation.NavController
import com.sample.radiostations.app.presentation.radiostation.shows.RadioStationShowsNavHostInfo.RADIO_STATION_ID_PARAM
import com.sample.radiostations.app.presentation.radiostation.shows.RadioStationShowsNavHostInfo.ROUTE

internal object RadioStationShowsNavHostInfo {
    const val RADIO_STATION_ID_PARAM = "radiostationid"
    const val ROUTE = "radio_station_shows_route?radiostationid={$RADIO_STATION_ID_PARAM}"
}

fun NavController.navigateToRadioStationShows(radioStationId: String) {
    navigate(ROUTE.replace("{$RADIO_STATION_ID_PARAM}", radioStationId))
}
