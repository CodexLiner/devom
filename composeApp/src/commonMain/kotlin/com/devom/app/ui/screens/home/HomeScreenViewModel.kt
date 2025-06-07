package com.devom.app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.app.models.STATUS
import com.devom.models.slots.GetBookingsResponse
import com.devom.models.slots.UpdateBookingStatusInput
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.network.onResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel(){

    private val _bookings = MutableStateFlow<List<GetBookingsResponse>>(listOf())
    val bookings: StateFlow<List<GetBookingsResponse>> = _bookings


    fun getBookings() {
        viewModelScope.launch {
            Project.pandit.getPanditBookingsUseCase.invoke(cachePolicy = CachePolicy.CacheAndNetwork).collect {
                it.onResult {
                    _bookings.value = it.data
                }
            }
        }
    }

    fun updateBookingStatus(id: Int, status: STATUS) {
        viewModelScope.launch {
            Project.pandit.updateBookingStatusUseCase.invoke(
                UpdateBookingStatusInput(
                    id = id,
                    status = status.status
                )
            ).collect {
                it.onResult {
                    getBookings()
                }
            }
        }
    }
}