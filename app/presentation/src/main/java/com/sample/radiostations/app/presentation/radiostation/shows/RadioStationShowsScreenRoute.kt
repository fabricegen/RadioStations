package com.sample.radiostations.app.presentation.radiostation.shows

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RadioStationShowsScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: RadioStationShowsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RadioStationShowsScreen(
        modifier = modifier,
        uiState = uiState
    )
}