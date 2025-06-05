package com.devom.domain.panditslots

import com.devom.data.repository.panditslots.PanditSlotsRemoteRepository
import com.devom.data.repository.panditslots.PanditSlotsRemoteRepositoryImpl
import com.devom.models.slots.UpdateBookingStatusInput

class UpdateBookingStatusUseCase {
    private val panditSlotsRemoteRepository: PanditSlotsRemoteRepository = PanditSlotsRemoteRepositoryImpl()
    suspend operator fun invoke(input: UpdateBookingStatusInput) = panditSlotsRemoteRepository.updateBookingStatus(input)
}