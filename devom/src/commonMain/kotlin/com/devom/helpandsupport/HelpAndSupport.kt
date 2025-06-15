package com.devom.helpandsupport

class HelpAndSupport {
    val getAllTicketsUseCase by lazy { com.devom.domain.helpandsupport.GetAllTicketsUseCase() }
    val createTicketUseCase by lazy { com.devom.domain.helpandsupport.CreateTicketUseCase() }
    val getTicketDetailsUseCase by lazy { com.devom.domain.helpandsupport.GetTicketDetailsUseCase() }
}