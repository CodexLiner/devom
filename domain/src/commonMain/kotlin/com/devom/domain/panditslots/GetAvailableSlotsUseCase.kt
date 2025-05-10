package com.devom.domain.panditslots

import com.devom.data.repository.panditslots.PanditSlotsRemoteRepository
import com.devom.data.repository.panditslots.PanditSlotsRemoteRepositoryImpl
import com.devom.utils.cachepolicy.CachePolicy

class GetAvailableSlotsUseCase {
    private val panditSlotsRemoteRepository: PanditSlotsRemoteRepository = PanditSlotsRemoteRepositoryImpl()
    suspend fun invoke(panditId : String , cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork) = panditSlotsRemoteRepository.getAvailableSlots(panditId , cachePolicy)
}