package com.radiostations.data.repository

import com.radiostations.app.data.api.RadiosApiService
import com.radiostations.app.data.api.request.GraphQlRequest
import com.radiostations.app.data.api.response.GetRadioStationShowsResponse
import com.radiostations.app.data.api.response.GetRadioStationsResponse
import com.radiostations.app.data.api.response.RadioStationsData
import com.radiostations.app.data.entity.RadioStation
import com.radiostations.app.data.entity.RadioStationShowConnection
import com.radiostations.app.data.entity.RadioStationShowConnectionEdge
import com.radiostations.app.data.entity.RadioStationShowConnectionEdgeNode
import com.radiostations.app.data.entity.RadioStationShowEdge
import com.radiostations.app.data.entity.RadioStationShowNode
import com.radiostations.app.data.entity.RadioStationShows
import com.radiostations.app.data.entity.RadioStationShowsData
import com.radiostations.app.data.repository.DefaultRemoteRadiosDataSource
import com.sample.radiostation.core.test.CoroutineDispatcherRule
import com.sample.radiostations.core.commons.api.dispatcher.CoroutineDispatchers
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DefaultRemoteRadiosDataSourceTest {
    @get:Rule
    val dispatchersRule = CoroutineDispatcherRule()

    @MockK
    private lateinit var radiosApiService: RadiosApiService

    @MockK
    private lateinit var coroutineDispatchers: CoroutineDispatchers

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given radio stations when success then return data`() {
        coEvery {
            radiosApiService.getRadioStations(
                GraphQlRequest.GetRadioStationsGraphQlRequest.build()
            )
        }.returns(mockGetRadioStationsResponse)
        coEvery { coroutineDispatchers.io } returns dispatchersRule.testDispatcher
        val radiosDataSource = DefaultRemoteRadiosDataSource(
            apiService = radiosApiService,
            coroutineDispatchers = coroutineDispatchers
        )

        runTest(dispatchersRule.testDispatcher) {
            val stations = radiosDataSource.getRadioStations()

            Assert.assertEquals(mockGetRadioStationsResponse.data.brands.size, stations.size)

            coVerify { radiosApiService.getRadioStations(any()) }
        }
    }

    @Test
    fun `given radio station shows when success then return data`() {
        coEvery {
            radiosApiService.getRadioStationShows(any())
        }.returns(mockGetRadioStationShowsResponse)
        coEvery { coroutineDispatchers.io } returns dispatchersRule.testDispatcher
        val radiosDataSource = DefaultRemoteRadiosDataSource(
            apiService = radiosApiService,
            coroutineDispatchers = coroutineDispatchers
        )

        runTest(dispatchersRule.testDispatcher) {
            val stations = radiosDataSource.getRadioStationShows("FRANCEINTER")

            Assert.assertTrue(stations.size == mockGetRadioStationShowsResponse.data.shows.edges.size)
            val station = stations.firstOrNull{ it.id == "1" }
            Assert.assertNotNull(station)
            Assert.assertNull(station?.description)
            Assert.assertNull(station?.subTitle)

            coVerify { radiosApiService.getRadioStationShows(any()) }
        }
    }

    private val mockGetRadioStationsResponse = GetRadioStationsResponse(
        data = RadioStationsData(
            brands = listOf(
                RadioStation(id = "1", title = "title1", baseline = "baseline1", description = "description1"),
                RadioStation(id = "2", title = "title2", baseline = "baseline2", description = "description2"),
                RadioStation(id = "3", title = "title3", baseline = "baseline3", description = "description3)")
            )
        )
    )
    private val mockGetRadioStationShowsResponse = GetRadioStationShowsResponse(
        data = RadioStationShowsData(
            shows = RadioStationShows(
                edges = listOf(
                    RadioStationShowEdge(
                        cursor = "",
                        node = RadioStationShowNode(
                            id = "1",
                            title = "title",
                            taxonomiesConnection = RadioStationShowConnection(
                                edges = listOf(
                                    RadioStationShowConnectionEdge(
                                        relation = "",
                                        info = "",
                                        node = RadioStationShowConnectionEdgeNode(
                                            id = "1", title = "title1", type = "baseline1", standFirst = "description1", path = ""
                                        )
                                    )
                                )
                            )
                        )
                    ),
                    RadioStationShowEdge(
                        cursor = "",
                        node = RadioStationShowNode(
                            id = "2",
                            title = "title",
                            taxonomiesConnection = RadioStationShowConnection(
                                edges = listOf(
                                    RadioStationShowConnectionEdge(
                                        relation = "theme",
                                        info = "",
                                        node = RadioStationShowConnectionEdgeNode(
                                            id = "1", title = "title1", type = "baseline1", standFirst = "description1", path = ""
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )
    )

}