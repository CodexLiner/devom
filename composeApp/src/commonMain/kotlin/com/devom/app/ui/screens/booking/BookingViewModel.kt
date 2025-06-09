package com.devom.app.ui.screens.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.app.models.ApplicationStatus
import com.devom.models.poojaitems.GetPoojaItemsResponse
import com.devom.models.slots.GetBookingsResponse
import com.devom.models.slots.UpdateBookingStatusInput
import com.devom.utils.Application
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.network.onResult
import com.devom.utils.network.withSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookingViewModel : ViewModel() {

    private val _bookings = MutableStateFlow<List<GetBookingsResponse>>(listOf())
    val bookings: StateFlow<List<GetBookingsResponse>> = _bookings
    private val _poojaItems = MutableStateFlow<List<GetPoojaItemsResponse>>(listOf())
    val poojaItems: StateFlow<List<GetPoojaItemsResponse>> = _poojaItems

    private val _bookingDetailItem =  MutableStateFlow<GetBookingsResponse?>(null)
    val bookingDetailItem : StateFlow<GetBookingsResponse?> = _bookingDetailItem


    fun getBookings() {
        viewModelScope.launch {
            Project.pandit.getPanditBookingsUseCase.invoke(cachePolicy = CachePolicy.CacheAndNetwork).collect {
                it.onResult {
                    _bookings.value = it.data
                }
            }
        }
    }

    fun getBookingById(bookingId: String) {
        viewModelScope.launch {
            Project.pandit.getPanditBookingById.invoke(bookingId).collect {
                it.onResult {
                    _bookingDetailItem.value = it.data
                }
            }
        }
    }

    fun updateBookingStatus(id: Int, applicationStatus: ApplicationStatus) {
        viewModelScope.launch {
            Project.pandit.updateBookingStatusUseCase.invoke(
                UpdateBookingStatusInput(
                    id = id,
                    status = applicationStatus.status
                )
            ).collect {
                it.onResult {
                    getBookings()
                    Application.showToast("Booking status updated successfully")
                }
            }
        }
    }

    fun getPoojaItems() {
        viewModelScope.launch {
            Project.poojaItem.getPoojaItemUseCase.invoke().collect {
                it.withSuccess {
                    _poojaItems.value = it.data
                }
            }
        }
    }

    fun addPoojaItem(string: String, booking: GetBookingsResponse) {
        viewModelScope.launch {
//            Project.pooja.addPoojaItemUseCase.invoke(string).collect {
//                it.withSuccessWithoutData {
//                    getBookings()
//                }
//            }
        }
    }
}