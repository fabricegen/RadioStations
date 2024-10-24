package com.sample.radiostations.app.presentation.radiostation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RadioStationsListScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: RadioStationListViewModel = hiltViewModel(),
    onClickRadioStation: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RadioStationsListScreen(
        modifier = modifier,
        uiState = uiState,
        onClickRadioStation = onClickRadioStation
    )
}