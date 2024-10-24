package com.sample.radiostations.app.presentation.radiostation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radiostations.core.design.system.RadioStationsTheme
import com.sample.radiostations.app.presentation.common.component.ErrorView
import com.sample.radiostations.app.presentation.common.component.LoaderView
import com.sample.radiostations.app.presentation.common.model.ErrorMessageUi
import com.sample.radiostations.app.presentation.radiostation.list.component.RadioStationsContentView
import com.sample.radiostations.app.presentation.radiostation.list.model.RadioStationUi
import kotlinx.collections.immutable.persistentListOf

@Composable
fun RadioStationsListScreen(
    modifier: Modifier = Modifier,
    uiState: RadioStationListUiState,
    onClickRadioStation: (String) -> Unit
) {
    Box(
        modifier = modifier.padding(8.dp).fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        uiState.errorMessage?.let {
            ErrorView(
                modifier = Modifier
                    .fillMaxSize(),
                message = it.message
            )
        } ?: run {
            if (uiState.loading) {
                LoaderView(
                    modifier = Modifier
                        .fillMaxSize()
                )
            } else {
                RadioStationsContentView(
                    modifier = Modifier,
                    radioStations = uiState.radioStations,
                    onClickRadioStation = onClickRadioStation
                )
            }
        }
    }
}

@Preview
@Composable
fun RadioStationListScreenPreview() {
    RadioStationsTheme {
        Surface {
            RadioStationsListScreen(
                modifier = Modifier,
                uiState = RadioStationListUiState(
                    loading = false,
                    persistentListOf(
                        RadioStationUi("1", "title1", "baseline1", "description1"),
                        RadioStationUi("2", "title2", "baseline2", "description2"),
                        RadioStationUi("3", "title3", "baseline3", "description3")
                    )
                ),
                onClickRadioStation = {}
            )
        }
    }
}

@Preview
@Composable
fun RadioStationListScreenOnErrorPreview() {
    RadioStationsTheme {
        Surface {
            RadioStationsListScreen(
                modifier = Modifier,
                uiState = RadioStationListUiState(
                    loading = false,
                    persistentListOf(),
                    errorMessage = ErrorMessageUi(message = "Unknown")
                ),
                onClickRadioStation = {}
            )
        }
    }
}
