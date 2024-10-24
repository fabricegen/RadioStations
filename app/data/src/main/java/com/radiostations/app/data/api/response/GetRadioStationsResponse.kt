package com.radiostations.app.data.api.response

import com.radiostations.app.data.entity.RadioStation
import kotlinx.serialization.Serializable

@Serializable
internal data class GetRadioStationsResponse(
    val data: RadioStationsData
)

@Serializable
internal data class RadioStationsData(
    val brands: List<RadioStation>
)
