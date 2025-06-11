package com.devom.domain.helpandsupport

import com.devom.data.repository.helpandsupport.HelpAndSupportRepositoryImpl
import com.devom.models.helpandsupport.CreateTicketRequest

class CreateTicketUseCase {
    private val  helpAndSupportRepository = HelpAndSupportRepositoryImpl()
    suspend operator fun invoke(createTicketRequest: CreateTicketRequest) = helpAndSupportRepository.createTicket(createTicketRequest)


}