package com.devom.data.server.panditslots

import com.devom.data.server.endpoints.PanditSlotsEndpoints
import com.devom.models.slots.BookPanditSlotInput
import com.devom.models.slots.CreatePanditSlotInput
import com.devom.models.slots.GetAvailableSlotsResponse
import com.devom.models.slots.GetBookingsResponse
import com.devom.models.slots.RemoveAndUpdatePoojaItemRequest
import com.devom.models.slots.UpdateBookingStatusInput
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

interface PanditSlotsRemoteDataSource {
    suspend fun getAvailableSlots(panditId: String): Flow<ResponseResult<List<GetAvailableSlotsResponse>>>
    suspend fun createPanditSlot(createPanditSlotInput: CreatePanditSlotInput): Flow<ResponseResult<String>>
    suspend fun bookPanditSlot(bookPanditSlotRequest: BookPanditSlotInput): Flow<ResponseResult<String>>
    suspend fun getBookings(): Flow<ResponseResult<List<GetBookingsResponse>>>
    suspend fun removePanditSlot(slotId: String): Flow<ResponseResult<List<GetAvailableSlotsResponse>>>
    suspend fun removeAndUpdateItemsInBooking(
        bookingId: String,
        input: RemoveAndUpdatePoojaItemRequest,
    ): Flow<ResponseResult<List<Int>>>

    suspend fun getBookingById(bookingId: String): Flow<ResponseResult<GetBookingsResponse>>
    suspend fun updateBookingStatus(input: UpdateBookingStatusInput): Flow<ResponseResult<String>>
}

class PanditSlotsRemoteDataSourceImpl() : PanditSlotsRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient
    override suspend fun getAvailableSlots(panditId: String): Flow<ResponseResult<List<GetAvailableSlotsResponse>>> =
        ktorClient.get(PanditSlotsEndpoints.GetAvailableSlots.plus("/$panditId"))
            .toResponseResult()

    override suspend fun createPanditSlot(createPanditSlotInput: CreatePanditSlotInput): Flow<ResponseResult<String>> =
        ktorClient.post(PanditSlotsEndpoints.CreatePanditSlot) { setBody(createPanditSlotInput) }
            .toResponseResult()

    override suspend fun bookPanditSlot(bookPanditSlotRequest: BookPanditSlotInput): Flow<ResponseResult<String>> =
        ktorClient.post(PanditSlotsEndpoints.BookPanditSlot) { setBody(bookPanditSlotRequest) }
            .toResponseResult()

    override suspend fun getBookings(): Flow<ResponseResult<List<GetBookingsResponse>>> =
        ktorClient.get(PanditSlotsEndpoints.GetBookings).toResponseResult()

    override suspend fun removePanditSlot(slotId: String): Flow<ResponseResult<List<GetAvailableSlotsResponse>>> =
        ktorClient.delete(PanditSlotsEndpoints.RemovePanditSlot.plus("/$slotId")).toResponseResult()

    override suspend fun removeAndUpdateItemsInBooking(
        bookingId: String,
        input: RemoveAndUpdatePoojaItemRequest,
    ): Flow<ResponseResult<List<Int>>> =
        ktorClient.post(PanditSlotsEndpoints.RemoveAndUpdateItemsInBooking.plus("/$bookingId/update-items")) {
            setBody(input)
        }.toResponseResult()


    override suspend fun getBookingById(bookingId: String): Flow<ResponseResult<GetBookingsResponse>> =
        ktorClient.get(PanditSlotsEndpoints.GetBookingById.plus("/$bookingId")).toResponseResult()

    override suspend fun updateBookingStatus(input: UpdateBookingStatusInput): Flow<ResponseResult<String>> =
        ktorClient.put(PanditSlotsEndpoints.UpdateBookingStatus) { setBody(input) }
            .toResponseResult()

}