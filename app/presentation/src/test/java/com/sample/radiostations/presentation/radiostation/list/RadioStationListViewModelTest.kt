package com.sample.radiostations.presentation.radiostation.list

import com.sample.radiostation.core.test.CoroutineDispatcherRule
import com.sample.radiostations.app.domain.entity.RadioStationDetail
import com.sample.radiostations.app.domain.usecase.GetRadioStationsUseCase
import com.sample.radiostations.app.presentation.radiostation.list.RadioStationListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RadioStationListViewModelTest {
    @get:Rule
    val dispatchersRule = CoroutineDispatcherRule()

    @MockK
    private lateinit var getRadioStationsUseCase: GetRadioStationsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given radio stations when success then return data`() {
        coEvery { getRadioStationsUseCase() }.returns(mockRadioStations)

        runTest(dispatchersRule.testDispatcher) {
            val viewModel = RadioStationListViewModel(getRadioStationsUseCase)

            Assert.assertEquals(viewModel.uiState.value.radioStations.size, mockRadioStations.size)

            coVerify { getRadioStationsUseCase.invoke() }
        }
    }

    private val mockRadioStations = listOf(
        RadioStationDetail(id = "1", title = "title1", baseline = "baseline1", description = "description1"),
        RadioStationDetail(id = "2", title = "title2", baseline = "baseline2", description = "description2"),
        RadioStationDetail(id = "3", title = "title3", baseline = "baseline3", description = "description3)")
    )
}