package com.radiostations.app.data.api.request

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

sealed class GraphQlRequest(
    val query: String
) {
    fun build() = JsonObject(mapOf(Pair("query", JsonPrimitive(query))))

    data object GetRadioStationsGraphQlRequest : GraphQlRequest(
        query = "{brands {id title baseline description}}"
    )

    data class GetRadioStationShowsGraphQlRequest(val radioStationId: String) : GraphQlRequest(
        query = "{shows(station: ${radioStationId}, first: 10) {edges {cursor node { id " +
            "title taxonomiesConnection { edges { relation info node { id path type title standFirst } } } } } } }"
    )

}