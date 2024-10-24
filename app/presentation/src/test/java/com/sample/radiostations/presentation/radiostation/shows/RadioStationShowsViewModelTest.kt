package com.sample.radiostations.presentation.radiostation.shows

import androidx.lifecycle.SavedStateHandle
import com.sample.radiostation.core.test.CoroutineDispatcherRule
import com.sample.radiostations.app.domain.entity.RadioStationShowDetail
import com.sample.radiostations.app.domain.usecase.GetRadioStationShowsUseCase
import com.sample.radiostations.app.presentation.radiostation.shows.RadioStationShowsNavHostInfo.RADIO_STATION_ID_PARAM
import com.sample.radiostations.app.presentation.radiostation.shows.RadioStationShowsViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RadioStationShowsViewModelTest {
    @get:Rule
    val dispatchersRule = CoroutineDispatcherRule()

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    @MockK
    private lateinit var getRadioStationShowsUseCase: GetRadioStationShowsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given radio station shows when success then return data`() {
        coEvery { getRadioStationShowsUseCase(any()) }.returns(mockRadioStationShows)
        val savedStateHandleFlow = MutableStateFlow<String?>(null)
        coEvery { savedStateHandle.getStateFlow<String?>(any(), null) }.returns(savedStateHandleFlow)

        runTest(dispatchersRule.testDispatcher) {
            savedStateHandleFlow.emit("FRANCEINTER")
            val viewModel = RadioStationShowsViewModel(savedStateHandle, getRadioStationShowsUseCase)

            Assert.assertEquals(viewModel.uiState.value.radioStationsShows.size, mockRadioStationShows.size)

            coVerify { getRadioStationShowsUseCase.invoke(any()) }
        }
    }

    private val mockRadioStationShows = listOf(
        RadioStationShowDetail(id = "1", title = "title1", subTitle = "subTitle1", description = "description1"),
        RadioStationShowDetail(id = "2", title = "title2", subTitle = "subTitle2", description = "description2"),
        RadioStationShowDetail(id = "3", title = "title3", subTitle = "subTitle3", description = "description3)")
    )
}