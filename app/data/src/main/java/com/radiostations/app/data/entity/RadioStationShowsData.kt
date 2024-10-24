package com.radiostations.app.data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class RadioStationShowsData(
    val shows: RadioStationShows
)

@Serializable
internal data class RadioStationShows(
    val edges: List<RadioStationShowEdge>
)

@Serializable
internal data class RadioStationShowEdge(
    val cursor: String,
    val node: RadioStationShowNode
)

@Serializable
internal data class RadioStationShowNode(
    val id: String,
    val title: String,
    val taxonomiesConnection: RadioStationShowConnection
)

@Serializable
internal data class RadioStationShowConnection(
    val edges: List<RadioStationShowConnectionEdge>?
)

@Serializable
internal data class RadioStationShowConnectionEdge(
    val relation: String,
    val info: String,
    val node: RadioStationShowConnectionEdgeNode?
)

@Serializable
internal data class RadioStationShowConnectionEdgeNode(
    val id: String,
    val path: String,
    val type: String,
    val title: String,
    val standFirst: String?
)

