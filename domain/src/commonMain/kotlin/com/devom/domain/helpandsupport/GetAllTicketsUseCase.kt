package com.devom.domain.helpandsupport

import com.devom.data.repository.helpandsupport.HelpAndSupportRepositoryImpl
import com.devom.utils.cachepolicy.CachePolicy

class GetAllTicketsUseCase {
    val helpAndSupportRepository = HelpAndSupportRepositoryImpl()
    suspend operator fun invoke(cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork) = helpAndSupportRepository.getAllTickets(cachePolicy)
}