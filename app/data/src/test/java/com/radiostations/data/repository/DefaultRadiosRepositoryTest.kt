package com.radiostations.data.repository

import com.radiostations.app.data.repository.DefaultRadiosRepository
import com.radiostations.app.data.repository.RemoteRadiosDataSource
import com.sample.radiostations.app.domain.entity.RadioStationDetail
import com.sample.radiostations.app.domain.entity.RadioStationShowDetail
import com.sample.radiostation.core.test.CoroutineDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DefaultRadiosRepositoryTest {
    @get:Rule
    val dispatchersRule = CoroutineDispatcherRule()

    @MockK
    private lateinit var remoteRadiosDataSource: RemoteRadiosDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given radio stations when success then return data`() {

        coEvery { remoteRadiosDataSource.getRadioStations() }.returns(mockRadioStations)
        val radiosRepository = DefaultRadiosRepository(remoteRadiosDataSource)

        runTest(dispatchersRule.testDispatcher) {
            val stations = radiosRepository.getRadioStations()

            Assert.assertEquals(mockRadioStations.size, stations.size)

            coVerify { remoteRadiosDataSource.getRadioStations() }
        }
    }

    @Test
    fun `given radio station shows when success then return data`() {
        coEvery { remoteRadiosDataSource.getRadioStationShows(any()) }.returns(mockRadioStationShows)
        val radiosRepository = DefaultRadiosRepository(remoteRadiosDataSource)

        runTest(dispatchersRule.testDispatcher) {
            val result = radiosRepository.getRadioStationShows("FRANCEINTER")

            Assert.assertEquals(mockRadioStationShows.size, result.size)

            coVerify { remoteRadiosDataSource.getRadioStationShows(any()) }
        }
    }

    private val mockRadioStations = listOf(
        RadioStationDetail(id = "1", title = "title1", baseline = "baseline1", description = "description1"),
        RadioStationDetail(id = "2", title = "title2", baseline = "baseline2", description = "description2"),
        RadioStationDetail(id = "3", title = "title3", baseline = "baseline3", description = "description3"),
    )
    private val mockRadioStationShows = listOf(
        RadioStationShowDetail(id = "1", title = "title1", subTitle = "subTitle1", description = "description1"),
        RadioStationShowDetail(id = "2", title = "title2", subTitle = "subTitle2", description = "description2"),
        RadioStationShowDetail(id = "3", title = "title3", subTitle = "subTitle3", description = "description3"),
    )
}