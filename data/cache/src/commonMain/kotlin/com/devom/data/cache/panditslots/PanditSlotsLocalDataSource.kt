package com.devom.data.cache.panditslots

import com.devom.data.cache.CacheHelper
import com.devom.data.cache.utils.jsonConfig
import com.devom.data.cache.utils.toCacheResult
import com.devom.models.slots.GetAvailableSlotsResponse
import com.devom.models.slots.GetBookingsResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PanditSlotsLocalDataSource {
    suspend fun getAvailableSlots(panditId: String): Flow<ResponseResult<List<GetAvailableSlotsResponse>>>
    suspend fun getBookings(): Flow<ResponseResult<List<GetBookingsResponse>>>
    suspend fun saveAvailableSlots(panditId : String ,data : List<GetAvailableSlotsResponse>)
    suspend fun saveBookings(data : List<GetBookingsResponse>)
}

class PanditSlotsLocalDataSourceImpl() : PanditSlotsLocalDataSource {
    private val cacheHelper = CacheHelper

    override suspend fun getAvailableSlots(panditId: String): Flow<ResponseResult<List<GetAvailableSlotsResponse>>> =
        cacheHelper.getData("getAvailableSlots$panditId").toCacheResult()

    override suspend fun saveAvailableSlots(panditId: String, data: List<GetAvailableSlotsResponse>) {
        cacheHelper.saveData("getAvailableSlots$panditId", jsonConfig.encodeToString(data))
    }

    override suspend fun saveBookings(data: List<GetBookingsResponse>) {
        cacheHelper.saveData("getBookings", jsonConfig.encodeToString(data))
    }

    override suspend fun getBookings(): Flow<ResponseResult<List<GetBookingsResponse>>> = cacheHelper.getData("getBookings").toCacheResult()

}