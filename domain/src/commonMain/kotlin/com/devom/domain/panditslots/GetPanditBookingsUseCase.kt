package com.devom.domain.panditslots

import com.devom.data.repository.panditslots.PanditSlotsRemoteRepository
import com.devom.data.repository.panditslots.PanditSlotsRemoteRepositoryImpl

class GetPanditBookingsUseCase {
    private val panditSlotsRemoteRepository: PanditSlotsRemoteRepository = PanditSlotsRemoteRepositoryImpl()
    suspend operator fun invoke() = panditSlotsRemoteRepository.getBookings()
}