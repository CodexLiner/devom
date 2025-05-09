package com.devom.domain.panditslots

import com.devom.data.repository.panditslots.PanditSlotsRemoteRepository
import com.devom.data.repository.panditslots.PanditSlotsRemoteRepositoryImpl
import com.devom.models.slots.BookPanditSlotInput

class BookPanditSlotUseCase {
    private val panditSlotsRemoteRepository: PanditSlotsRemoteRepository = PanditSlotsRemoteRepositoryImpl()
    suspend fun invoke(input : BookPanditSlotInput) = panditSlotsRemoteRepository.bookPanditSlot(input)
}