package com.devom.app.ui.screens.helpandsupport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.helpandsupport.CreateTicketRequest
import com.devom.models.helpandsupport.GetAllTicketsResponse
import com.devom.utils.network.onResult
import com.devom.utils.network.onResultNothing
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HelpAndSupportViewModel : ViewModel() {
    private val _tickets = MutableStateFlow<List<GetAllTicketsResponse>>(listOf())
    val tickets = _tickets.asStateFlow()

    init {
        getAllTickets()
    }

    fun getAllTickets() {
        viewModelScope.launch {
            Project.helpAndSupport.getAllTicketsUseCase.invoke().collect {
                it.onResult {
                    _tickets.value = it.data
                }
            }
        }
    }

    fun createTicket(request: CreateTicketRequest) {
        viewModelScope.launch {
            Project.helpAndSupport.createTicketUseCase.invoke(request).collect {
                it.onResultNothing {
                    getAllTickets()
                }
            }
        }
    }
}