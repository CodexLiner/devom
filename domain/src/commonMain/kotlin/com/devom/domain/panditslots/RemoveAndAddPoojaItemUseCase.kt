package com.devom.domain.panditslots

import com.devom.data.repository.panditslots.PanditSlotsRemoteRepository
import com.devom.data.repository.panditslots.PanditSlotsRemoteRepositoryImpl
import com.devom.models.slots.RemoveAndUpdatePoojaItemRequest

class RemoveAndAddPoojaItemUseCase {
    private val panditSlotsRemoteRepository: PanditSlotsRemoteRepository =
        PanditSlotsRemoteRepositoryImpl()

    suspend operator fun invoke(
        bookingId: String,
        input: RemoveAndUpdatePoojaItemRequest,

        ) = panditSlotsRemoteRepository.removeAndUpdateItemsInBooking(bookingId, input)
}