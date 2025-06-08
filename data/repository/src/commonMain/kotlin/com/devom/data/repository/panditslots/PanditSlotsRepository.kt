package com.devom.data.repository.panditslots

import com.devom.utils.cachepolicy.CachePolicy
import com.devom.data.cache.panditslots.PanditSlotsLocalDataSource
import com.devom.data.cache.panditslots.PanditSlotsLocalDataSourceImpl
import com.devom.data.server.panditslots.PanditSlotsRemoteDataSource
import com.devom.data.server.panditslots.PanditSlotsRemoteDataSourceImpl
import com.devom.models.slots.BookPanditSlotInput
import com.devom.models.slots.CreatePanditSlotInput
import com.devom.models.slots.GetAvailableSlotsResponse
import com.devom.models.slots.GetBookingsResponse
import com.devom.models.slots.UpdateBookingStatusInput
import com.devom.utils.flow.apiFlow
import com.devom.utils.flow.cacheAwareFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PanditSlotsRemoteRepository {
    suspend fun getAvailableSlots(panditId: String, cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork): Flow<ResponseResult<List<GetAvailableSlotsResponse>>>
    suspend fun createPanditSlot(createPanditSlotInput: CreatePanditSlotInput): Flow<ResponseResult<String>>
    suspend fun bookPanditSlot(bookPanditSlotRequest: BookPanditSlotInput): Flow<ResponseResult<String>>
    suspend fun getBookings(cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork): Flow<ResponseResult<List<GetBookingsResponse>>>
    suspend fun getBookingById(bookingId: String): Flow<ResponseResult<GetBookingsResponse>>
    suspend fun updateBookingStatus(input: UpdateBookingStatusInput): Flow<ResponseResult<String>>
}

class PanditSlotsRemoteRepositoryImpl : PanditSlotsRemoteRepository {
    private val panditSlotsRemoteDataSource: PanditSlotsRemoteDataSource =
        PanditSlotsRemoteDataSourceImpl()
    private val panditSlotsLocalDataSource: PanditSlotsLocalDataSource =
        PanditSlotsLocalDataSourceImpl()

    override suspend fun getAvailableSlots(
        panditId: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<List<GetAvailableSlotsResponse>>> =
        cacheAwareFlow(cachePolicy = cachePolicy, fetchCache = {
            panditSlotsLocalDataSource.getAvailableSlots(panditId)
        }, saveCache = {
            panditSlotsLocalDataSource.saveAvailableSlots(panditId, it)
        }, fetchNetwork = {
            panditSlotsRemoteDataSource.getAvailableSlots(panditId)
        })

    override suspend fun getBookings(cachePolicy: CachePolicy): Flow<ResponseResult<List<GetBookingsResponse>>> =
        cacheAwareFlow(cachePolicy = cachePolicy, fetchCache = {
            panditSlotsLocalDataSource.getBookings()
        }, fetchNetwork = {
            panditSlotsRemoteDataSource.getBookings()
        }, saveCache = {
            panditSlotsLocalDataSource.saveBookings(it)
        })

    override suspend fun getBookingById(bookingId: String): Flow<ResponseResult<GetBookingsResponse>> = apiFlow {
            panditSlotsRemoteDataSource.getBookingById(bookingId)
    }

    override suspend fun updateBookingStatus(input: UpdateBookingStatusInput): Flow<ResponseResult<String>> = apiFlow {
        panditSlotsRemoteDataSource.updateBookingStatus(input)
    }

    override suspend fun createPanditSlot(createPanditSlotInput: CreatePanditSlotInput): Flow<ResponseResult<String>> =
        apiFlow {
            panditSlotsRemoteDataSource.createPanditSlot(createPanditSlotInput)
        }

    override suspend fun bookPanditSlot(bookPanditSlotRequest: BookPanditSlotInput): Flow<ResponseResult<String>> =
        apiFlow {
            panditSlotsRemoteDataSource.bookPanditSlot(bookPanditSlotRequest)
        }
}