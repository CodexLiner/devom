package com.devom.domain.helpandsupport

import com.devom.data.repository.helpandsupport.HelpAndSupportRepositoryImpl

class GetTicketDetailsUseCase {
    private val helpAndSupportRepository = HelpAndSupportRepositoryImpl()
    suspend operator fun invoke(ticketId: String) = helpAndSupportRepository.getTicketDetails(ticketId)
}