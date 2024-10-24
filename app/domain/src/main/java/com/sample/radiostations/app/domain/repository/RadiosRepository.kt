package com.sample.radiostations.app.domain.repository

import com.sample.radiostations.app.domain.entity.RadioStationDetail
import com.sample.radiostations.app.domain.entity.RadioStationShowDetail

interface RadiosRepository {
    suspend fun getRadioStations(): List<RadioStationDetail>
    suspend fun getRadioStationShows(radioStationId: String): List<RadioStationShowDetail>
}