package com.radiostations.app.data.repository

import com.sample.radiostations.app.domain.entity.RadioStationDetail
import com.sample.radiostations.app.domain.entity.RadioStationShowDetail

interface RemoteRadiosDataSource {
    suspend fun getRadioStations(): List<RadioStationDetail>

    suspend fun getRadioStationShows(radioStationId: String): List<RadioStationShowDetail>
}
