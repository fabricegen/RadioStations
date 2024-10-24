package com.radiostations.app.data.repository

import com.sample.radiostations.app.domain.entity.RadioStationDetail
import com.sample.radiostations.app.domain.entity.RadioStationShowDetail
import com.sample.radiostations.app.domain.repository.RadiosRepository
import javax.inject.Inject

class DefaultRadiosRepository @Inject constructor(private val remoteRadiosDataSource: RemoteRadiosDataSource) :
    RadiosRepository {
    override suspend fun getRadioStations(): List<RadioStationDetail> = remoteRadiosDataSource.getRadioStations()

    override suspend fun getRadioStationShows(radioStationId: String): List<RadioStationShowDetail> =
        remoteRadiosDataSource.getRadioStationShows(radioStationId)
}