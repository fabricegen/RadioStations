package com.radiostations.app.data.api.response

import com.radiostations.app.data.entity.RadioStationShowsData
import kotlinx.serialization.Serializable

@Serializable
internal data class GetRadioStationShowsResponse(
    val data: RadioStationShowsData
)

