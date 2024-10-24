package com.radiostations.app.data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class RadioStation(
    val id: String,
    val title: String,
    val baseline: String?,
    val description: String
)
