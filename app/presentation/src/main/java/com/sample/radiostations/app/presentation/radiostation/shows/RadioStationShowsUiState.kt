package com.sample.radiostations.app.presentation.radiostation.shows

import com.sample.radiostations.app.presentation.common.model.ErrorMessageUi
import com.sample.radiostations.app.presentation.radiostation.shows.model.RadioStationShowUi
import kotlinx.collections.immutable.ImmutableList

data class RadioStationShowsUiState(
    val loading: Boolean = false,
    val radioStationsShows: ImmutableList<RadioStationShowUi>,
    val errorMessage: ErrorMessageUi? = null
)