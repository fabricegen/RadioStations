package com.radiostations.app.data.repository

import com.radiostations.app.data.api.RadiosApiService
import com.radiostations.app.data.api.request.GraphQlRequest.GetRadioStationShowsGraphQlRequest
import com.radiostations.app.data.api.request.GraphQlRequest.GetRadioStationsGraphQlRequest
import com.radiostations.app.data.api.response.GetRadioStationShowsResponse
import com.radiostations.app.data.api.response.GetRadioStationsResponse
import com.sample.radiostations.app.domain.entity.RadioStationDetail
import com.sample.radiostations.app.domain.entity.RadioStationShowDetail
import javax.inject.Inject

internal class DefaultRemoteRadiosDataSource @Inject constructor(
    private val apiService: RadiosApiService
) : RemoteRadiosDataSource {
    override suspend fun getRadioStations(): List<RadioStationDetail> =
        apiService.getRadioStations(GetRadioStationsGraphQlRequest.build())
            .mapToRadioStations()

    private fun GetRadioStationsResponse.mapToRadioStations(): List<RadioStationDetail> {
        return data.brands.map {
            RadioStationDetail(
                id = it.id,
                title = it.title,
                baseline = it.baseline,
                description = it.description
            )
        }
    }

    override suspend fun getRadioStationShows(radioStationId: String): List<RadioStationShowDetail> =
        apiService.getRadioStationShows(GetRadioStationShowsGraphQlRequest(radioStationId).build())
            .mapToRadioStationShows()

    private fun GetRadioStationShowsResponse.mapToRadioStationShows(): List<RadioStationShowDetail> {
        return data.shows.edges.map { radioStationShowEdge ->
            val radioStationShowConnectionEdge =
                radioStationShowEdge.node.taxonomiesConnection.edges?.firstOrNull() {
                    it.relation == "theme" || it.relation == "subtheme" || it.relation == "subsubtheme"
                }

            RadioStationShowDetail(
                id = radioStationShowEdge.node.id,
                title = radioStationShowEdge.node.title,
                subTitle = radioStationShowConnectionEdge?.node?.title,
                description = radioStationShowConnectionEdge?.node?.standFirst
            )
        }
    }
}
