package com.devom.data.server.panditslots

import com.devom.data.server.endpoints.PanditSlotsEndpoints
import com.devom.models.slots.BookPanditSlotInput
import com.devom.models.slots.CreatePanditSlotInput
import com.devom.models.slots.GetAvailableSlotsResponse
import com.devom.models.slots.GetBookingsResponse
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

interface PanditSlotsRemoteDataSource {
    suspend fun getAvailableSlots(panditId: String): Flow<ResponseResult<List<GetAvailableSlotsResponse>>>
    suspend fun createPanditSlot(createPanditSlotInput: CreatePanditSlotInput): Flow<ResponseResult<String>>
    suspend fun bookPanditSlot(bookPanditSlotRequest: BookPanditSlotInput): Flow<ResponseResult<String>>
    suspend fun getBookings(): Flow<ResponseResult<List<GetBookingsResponse>>>
}

class PanditSlotsRemoteDataSourceImpl() : PanditSlotsRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient
    override suspend fun getAvailableSlots(panditId: String): Flow<ResponseResult<List<GetAvailableSlotsResponse>>> =
        ktorClient.get(PanditSlotsEndpoints.GetAvailableSlots.plus("/$panditId"))
            .toResponseResult()

    override suspend fun createPanditSlot(createPanditSlotInput: CreatePanditSlotInput): Flow<ResponseResult<String>> =
        ktorClient.post(PanditSlotsEndpoints.CreatePanditSlot) { setBody(createPanditSlotInput) }.toResponseResult()

    override suspend fun bookPanditSlot(bookPanditSlotRequest: BookPanditSlotInput): Flow<ResponseResult<String>> =
        ktorClient.post(PanditSlotsEndpoints.BookPanditSlot) { setBody(bookPanditSlotRequest) }.toResponseResult()

    override suspend fun getBookings(): Flow<ResponseResult<List<GetBookingsResponse>>> =
        ktorClient.get(PanditSlotsEndpoints.GetBookings).toResponseResult()
}