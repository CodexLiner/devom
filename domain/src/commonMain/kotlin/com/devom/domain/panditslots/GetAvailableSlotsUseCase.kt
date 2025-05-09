package com.devom.domain.panditslots

import com.devom.data.repository.panditslots.PanditSlotsRemoteRepository
import com.devom.data.repository.panditslots.PanditSlotsRemoteRepositoryImpl

class GetAvailableSlotsUseCase {
    private val panditSlotsRemoteRepository: PanditSlotsRemoteRepository = PanditSlotsRemoteRepositoryImpl()
    suspend fun invoke(panditId : String) = panditSlotsRemoteRepository.getAvailableSlots(panditId)
}