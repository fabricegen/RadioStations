package com.sample.radiostations.app.presentation.radiostation.list

import com.sample.radiostations.app.presentation.radiostation.list.model.RadioStationUi
import com.sample.radiostations.app.presentation.common.model.ErrorMessageUi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class RadioStationListUiState(
    val loading: Boolean = false,
    val radioStations: ImmutableList<RadioStationUi> = persistentListOf(),
    val errorMessage: ErrorMessageUi? = null
)