package com.sample.radiostations.app.presentation.radiostation.list

import androidx.lifecycle.viewModelScope
import com.sample.radiostations.app.presentation.common.model.ErrorMessageUi
import com.sample.radiostations.app.domain.entity.RadioStationDetail
import com.sample.radiostations.app.domain.usecase.GetRadioStationsUseCase
import com.sample.radiostations.core.commons.presentation.BaseViewModel
import com.sample.radiostations.app.presentation.radiostation.list.model.RadioStationUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RadioStationListViewModel @Inject constructor(
    private val getRadioStationsUseCase: GetRadioStationsUseCase
) : BaseViewModel<RadioStationListAction, RadioStationListUiState>(RadioStationListUiState(loading = true)) {

    init {
        viewModelScope.launch {
            try {
                val stations = getRadioStationsUseCase()
                updateState {
                    copy(
                        loading = false,
                        radioStations = stations
                            .toUiRadioStations()
                            .toImmutableList()
                    )
                }
            } catch (e: Throwable) {
                Timber.e(e, "Unexpected error happened on getting radio stations")
                updateState {
                    copy(
                        errorMessage = ErrorMessageUi(message = e.javaClass.toString()),
                        loading = false
                    )
                }
            }
        }
    }

    override fun handle(action: RadioStationListAction) {
    }

    private fun List<RadioStationDetail>.toUiRadioStations(): List<RadioStationUi> =
        map {
            RadioStationUi(
                id = it.id,
                title = it.title,
                baseline = it.baseline,
                description = it.description
            )
        }
}
