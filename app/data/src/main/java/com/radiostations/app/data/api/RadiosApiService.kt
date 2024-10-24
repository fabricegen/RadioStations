package com.radiostations.app.data.api

import com.radiostations.app.data.api.response.GetRadioStationShowsResponse
import com.radiostations.app.data.api.response.GetRadioStationsResponse
import kotlinx.serialization.json.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

internal interface RadiosApiService {
    @POST("/v1/graphql")
    suspend fun getRadioStations(
        @Body body: JsonObject,
    ): GetRadioStationsResponse

    @POST("/v1/graphql")
    suspend fun getRadioStationShows(
        @Body body: JsonObject,
    ): GetRadioStationShowsResponse
}
