package com.sample.radiostations.app.presentation.radiostation.shows

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sample.radiostations.app.domain.entity.RadioStationShowDetail
import com.sample.radiostations.app.domain.usecase.GetRadioStationShowsUseCase
import com.sample.radiostations.app.presentation.common.model.ErrorMessageUi
import com.sample.radiostations.app.presentation.radiostation.shows.RadioStationShowsNavHostInfo.RADIO_STATION_ID_PARAM
import com.sample.radiostations.app.presentation.radiostation.shows.model.RadioStationShowUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RadioStationShowsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRadioStationShowsUseCase: GetRadioStationShowsUseCase,
) : com.sample.radiostations.core.commons.presentation.BaseViewModel<RadioStationShowsAction, RadioStationShowsUiState>(
    RadioStationShowsUiState(loading = true, radioStationsShows = persistentListOf())
) {

    init {
        viewModelScope.launch {
            updateState { copy(loading = true) }
            try {
                savedStateHandle.getStateFlow<String?>(RADIO_STATION_ID_PARAM, null)
                    .filterNotNull()
                    .collectLatest { radioStationId ->
                        val shows = getRadioStationShowsUseCase(radioStationId)
                        updateState {
                            copy(
                                loading = false,
                                radioStationsShows = shows
                                    .toRadioStationShowsUi()
                                    .toImmutableList()
                            )
                        }
                    }
            } catch (e: Throwable) {
                Timber.e(e, "Unexpected error happened on getting radio stations shows")
                updateState {
                    copy(
                        errorMessage = ErrorMessageUi(message = e.javaClass.toString()),
                        loading = false
                    )
                }
            }
        }
    }

    override fun handle(action: RadioStationShowsAction) {
    }

    private fun List<RadioStationShowDetail>.toRadioStationShowsUi() =
        map {
            RadioStationShowUi(
                id = it.id,
                title = it.title,
                subTitle = it.subTitle ?: "",
                description = it.description
            )
        }
}