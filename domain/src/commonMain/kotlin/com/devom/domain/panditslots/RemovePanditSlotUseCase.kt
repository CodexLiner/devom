package com.devom.domain.panditslots

import com.devom.data.repository.panditslots.PanditSlotsRemoteRepository
import com.devom.data.repository.panditslots.PanditSlotsRemoteRepositoryImpl
import com.devom.models.slots.CreatePanditSlotInput

class RemovePanditSlotUseCase {
    private val panditSlotsRemoteRepository: PanditSlotsRemoteRepository =
        PanditSlotsRemoteRepositoryImpl()

    suspend operator fun invoke(slotId: String) =
        panditSlotsRemoteRepository.removePanditSlot(slotId)
}