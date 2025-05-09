package com.devom.data.repository.panditslots

import com.devom.data.server.panditslots.PanditSlotsRemoteDataSource
import com.devom.data.server.panditslots.PanditSlotsRemoteDataSourceImpl
import com.devom.models.slots.BookPanditSlotInput
import com.devom.models.slots.CreatePanditSlotInput
import com.devom.models.slots.GetAvailableSlotsResponse
import com.devom.models.slots.GetBookingsResponse
import com.devom.utils.flow.apiFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PanditSlotsRemoteRepository {
    suspend fun getAvailableSlots(panditId: String): Flow<ResponseResult<List<GetAvailableSlotsResponse>>>
    suspend fun createPanditSlot(createPanditSlotInput: CreatePanditSlotInput): Flow<ResponseResult<String>>
    suspend fun bookPanditSlot(bookPanditSlotRequest: BookPanditSlotInput): Flow<ResponseResult<String>>
    suspend fun getBookings(): Flow<ResponseResult<List<GetBookingsResponse>>>
}

class PanditSlotsRemoteRepositoryImpl : PanditSlotsRemoteRepository {
    private val panditSlotsRemoteDataSource: PanditSlotsRemoteDataSource =
        PanditSlotsRemoteDataSourceImpl()

    override suspend fun getAvailableSlots(panditId: String): Flow<ResponseResult<List<GetAvailableSlotsResponse>>> = apiFlow {
            panditSlotsRemoteDataSource.getAvailableSlots(panditId)
        }

    override suspend fun createPanditSlot(createPanditSlotInput: CreatePanditSlotInput): Flow<ResponseResult<String>> = apiFlow {
            panditSlotsRemoteDataSource.createPanditSlot(createPanditSlotInput)
        }

    override suspend fun bookPanditSlot(bookPanditSlotRequest: BookPanditSlotInput): Flow<ResponseResult<String>> = apiFlow {
            panditSlotsRemoteDataSource.bookPanditSlot(bookPanditSlotRequest)
        }

    override suspend fun getBookings(): Flow<ResponseResult<List<GetBookingsResponse>>> = apiFlow {
        panditSlotsRemoteDataSource.getBookings()
    }

}