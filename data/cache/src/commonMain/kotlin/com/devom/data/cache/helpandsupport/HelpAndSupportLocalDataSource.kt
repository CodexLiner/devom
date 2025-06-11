package com.devom.data.cache.helpandsupport

import com.devom.data.cache.CacheHelper
import com.devom.data.cache.utils.jsonConfig
import com.devom.data.cache.utils.toCacheResult
import com.devom.models.helpandsupport.GetAllTicketsResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface HelpAndSupportLocalDataSource {
    suspend fun getAllTickets(): Flow<ResponseResult<List<GetAllTicketsResponse>>>
    suspend fun saveAllTickets(data: List<GetAllTicketsResponse>)
}

class HelpAndSupportLocalDataSourceImpl : HelpAndSupportLocalDataSource {
    private val cacheHelper = CacheHelper
    override suspend fun getAllTickets(): Flow<ResponseResult<List<GetAllTicketsResponse>>> =
        cacheHelper.getData("getAllTickets").toCacheResult()

    override suspend fun saveAllTickets(data: List<GetAllTicketsResponse>) {
        cacheHelper.saveData("getAllTickets", jsonConfig.encodeToString(data))
    }
}