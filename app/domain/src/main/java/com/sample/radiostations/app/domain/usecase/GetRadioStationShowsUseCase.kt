package com.sample.radiostations.app.domain.usecase

import com.sample.radiostations.app.domain.entity.RadioStationShowDetail
import com.sample.radiostations.app.domain.repository.RadiosRepository
import jakarta.inject.Inject

class GetRadioStationShowsUseCase @Inject constructor(
    private val radiosRepository: RadiosRepository
) {
    suspend operator fun invoke(radioStationId: String): List<RadioStationShowDetail> =
        radiosRepository.getRadioStationShows(radioStationId = radioStationId)
}