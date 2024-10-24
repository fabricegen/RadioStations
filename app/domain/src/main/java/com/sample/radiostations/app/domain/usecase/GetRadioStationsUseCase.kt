package com.sample.radiostations.app.domain.usecase

import com.sample.radiostations.app.domain.entity.RadioStationDetail
import com.sample.radiostations.app.domain.repository.RadiosRepository
import jakarta.inject.Inject

class GetRadioStationsUseCase @Inject constructor(
    private val radiosRepository: RadiosRepository
) {
    suspend operator fun invoke(): List<RadioStationDetail> = radiosRepository.getRadioStations()
}