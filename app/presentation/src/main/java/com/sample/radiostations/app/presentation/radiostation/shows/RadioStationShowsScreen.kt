package com.sample.radiostations.app.presentation.radiostation.shows

import androidx.compose.foundation.background
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
import com.sample.radiostations.app.presentation.radiostation.shows.component.RadioStationsShowsContentView
import com.sample.radiostations.app.presentation.radiostation.shows.model.RadioStationShowUi
import kotlinx.collections.immutable.persistentListOf

@Composable
fun RadioStationShowsScreen(
    modifier: Modifier = Modifier,
    uiState: RadioStationShowsUiState
) {
    Box(
        modifier = modifier
            .background(RadioStationsTheme.colorScheme.surface)
            .padding(8.dp)
            .fillMaxSize(),
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
                RadioStationsShowsContentView(
                    modifier = Modifier,
                    radioStationShows = uiState.radioStationsShows
                )
            }
        }
    }
}

@Preview
@Composable
fun RadioStationShowsScreenPreview() {
    RadioStationsTheme {
        Surface {
            RadioStationShowsScreen(
                modifier = Modifier,
                uiState = RadioStationShowsUiState(
                    loading = false,
                    persistentListOf(
                        RadioStationShowUi("1", "title1", "subTitle1", "description1"),
                        RadioStationShowUi("2", "title2", "subTitle2", "description2"),
                        RadioStationShowUi("3", "title3", "subTitle3", "description3")
                    )
                )
            )
        }
    }
}
