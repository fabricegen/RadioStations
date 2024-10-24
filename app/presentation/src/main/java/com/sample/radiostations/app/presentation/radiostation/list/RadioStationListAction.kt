package com.sample.radiostations.app.presentation.radiostation.list

sealed class RadioStationListAction {
    data class GetItemDetails(val itemId: String)
}