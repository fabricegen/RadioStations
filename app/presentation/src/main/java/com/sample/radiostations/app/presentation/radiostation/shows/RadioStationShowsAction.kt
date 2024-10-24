package com.sample.radiostations.app.presentation.radiostation.shows

sealed class RadioStationShowsAction {
    data class GetItemDetails(val itemId: String): RadioStationShowsAction()
}